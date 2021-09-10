package com.base.utils.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class CommonThreadPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonThreadPool.class);
    public static final String LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static ExecutorService execute = init();
    private static final long EXECUTETIME = 10000L;
    private static final String DEFAULT_THREAD_NAME = "base-threadPool-";

    private CommonThreadPool() {
    }

    public static Future<Object> execute(IAsynchronousHandler command) {
        ThreadPoolAdaptor handler = new ThreadPoolAdaptor(command, EXECUTETIME);
        return execute.submit(handler);
    }

    private static boolean shutDown() {
        if (execute != null) {
            execute.shutdown();
            return true;
        } else {
            return false;
        }
    }

    public static ThreadPoolExecutorExtend getThreadPool(ThreadPoolParameterVO vo) {
        int corePoolSize = vo.getCorePoolSize();
        int maximumPoolSize = vo.getMaximumPoolSize();
        int initialCapacity = vo.getInitialCapacity();
        long keepAliveTime = vo.getKeepAliveTime();
        String threadName = vo.getThreadName();
        TaskQueue taskqueue = new TaskQueue(initialCapacity, vo.isDiscard());
        ThreadPoolExecutorExtend executeNew = new ThreadPoolExecutorExtend(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, taskqueue, new TaskThreadFactory(threadName), new ThreadPlloRejectedExecutionHandler(vo.isDiscard()));
        executeNew.setThreadPoolName(threadName);
        taskqueue.setParent(executeNew);
        return executeNew;
    }

    private static ExecutorService init() {
        Properties ps = getThreadPoolConfig();
        int corePoolSize = 5;
        int maximumPoolSize = 120;
        int initialCapacity = 20000;
        long keepAliveTime = 120L;
        String threadName = DEFAULT_THREAD_NAME;
        if (null != ps) {
            corePoolSize = Integer.parseInt(ps.getProperty("corePoolSize", "5"));
            maximumPoolSize = Integer.parseInt(ps.getProperty("maximumPoolSize", "120"));
            initialCapacity = Integer.parseInt(ps.getProperty("initialCapacity", "20000"));
            keepAliveTime = Long.parseLong(ps.getProperty("keepAliveTime", "120"));
            threadName = ps.getProperty("threadName", DEFAULT_THREAD_NAME);
        }
        ThreadPoolParameterVO vo = new ThreadPoolParameterVO();
        vo.setCorePoolSize(corePoolSize);
        vo.setMaximumPoolSize(maximumPoolSize);
        vo.setInitialCapacity(initialCapacity);
        vo.setKeepAliveTime(keepAliveTime);
        vo.setMaximumPoolSize(maximumPoolSize);
        vo.setThreadName(threadName);
        vo.setDiscard(false);
        return getThreadPool(vo);
    }

    private static Properties getThreadPoolConfig() {
        Properties ps = new Properties();
        InputStream in = CommonThreadPool.class.getResourceAsStream("/threadPoolConfig.properties");
        if (in == null) {
            return null;
        } else {
            try {
                ps.load(in);
                return ps;
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    public static boolean isMemoryThreshold() {
        long size = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        long thresholdSize = (long) ((double) ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax() * 0.7D);
        return size > thresholdSize;
    }

    static class ThreadPlloRejectedExecutionHandler implements RejectedExecutionHandler {
        boolean isDiscard = true;

        public ThreadPlloRejectedExecutionHandler() {
        }

        public ThreadPlloRejectedExecutionHandler(boolean isDiscard) {
            this.isDiscard = isDiscard;
        }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            BlockingQueue queue;
            if (this.shouldNotReject()) {
                try {
                    boolean reAdd = false;
                    queue = executor.getQueue();
                    if (queue instanceof TaskQueue) {
                        reAdd = ((TaskQueue) queue).superOffer(r);
                    } else {
                        reAdd = executor.getQueue().offer(r);
                    }

                    if (reAdd) {
                        return;
                    }
                } catch (Exception var6) {
                    throw new RejectedExecutionException(var6);
                }
            }

            IAsynchronousHandler handlerAdaptor;
            if (r instanceof CommonFutureTask) {
                handlerAdaptor = ((CommonFutureTask) r).getR();
                if (handlerAdaptor == null) {
                    CommonThreadPool.LOGGER.info("CommonThreadPool 以达到队列容量上限：{}", r.toString());
                    throw new RejectedExecutionException();
                }
            }

            try {
                if (r instanceof CommonFutureTask) {
                    handlerAdaptor = ((CommonFutureTask) r).getR();
                    queue = null;
                    IAsynchronousHandler handler;
                    if (handlerAdaptor instanceof ThreadPoolAdaptor) {
                        handler = ((ThreadPoolAdaptor) handlerAdaptor).getHandler();
                        if (handler == null) {
                            handler = handlerAdaptor;
                        }
                    } else {
                        handler = handlerAdaptor;
                    }

                    StringBuilder sb = new StringBuilder();
                    sb.append("任务名称:").append(handler.getClass())
                            .append("。happenTime=").append(this.formateDate())
                            .append("。toString=").append(handler.toString());
                    CommonThreadPool.LOGGER.info("CommonThreadPool 以达到队列容量上限：{}", sb.toString());
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("任务名称:").append(r.getClass())
                            .append("。happenTime=").append(this.formateDate())
                            .append("。toString=").append(r.toString());
                    CommonThreadPool.LOGGER.info("CommonThreadPool 以达到队列容量上限：{}", sb.toString());
                }

                if (executor instanceof ThreadPoolExecutorExtend) {
                    ((ThreadPoolExecutorExtend) executor).getSubmittedTasksCount().decrementAndGet();
                }
            } catch (Exception var7) {
                CommonThreadPool.LOGGER.error(var7.getMessage());
                throw new RejectedExecutionException(var7);
            }

            throw new RejectedExecutionException();
        }

        private boolean shouldNotReject() {
            return !this.isDiscard || this.isDiscard && !CommonThreadPool.isMemoryThreshold();
        }

        private String formateDate() {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(LONG_FORMAT);
            return sdf.format(date);
        }
    }

    static class TaskQueue extends LinkedBlockingQueue<Runnable> {
        private static final long serialVersionUID = -3385739834299189387L;
        ThreadPoolExecutorExtend parent = null;
        boolean isDiscard = true;

        public TaskQueue() {
        }

        public TaskQueue(int initialCapacity) {
            super(initialCapacity);
        }

        public TaskQueue(int initialCapacity, boolean isDiscard) {
            super(initialCapacity);
            this.isDiscard = isDiscard;
        }

        public TaskQueue(Collection<? extends Runnable> c) {
            super(c);
        }

        public void setParent(ThreadPoolExecutorExtend tp) {
            this.parent = tp;
        }

        public boolean force(Runnable o) {
            if (this.parent.isShutdown()) {
                throw new RejectedExecutionException("Executor not running, can't force a command into the queue");
            } else {
                return super.offer(o);
            }
        }

        public boolean offer(Runnable o) {
            if (this.parent == null) {
                return super.offer(o);
            } else if (this.isDiscard && CommonThreadPool.isMemoryThreshold()) {
                return false;
            } else if (this.parent.getPoolSize() == this.parent.getMaximumPoolSize()) {
                return super.offer(o);
            } else {
                AtomicInteger submittedTasksCountNew = this.parent.submittedTasksCount;
                if (submittedTasksCountNew != null && submittedTasksCountNew.get() <= this.parent.getPoolSize()) {
                    return super.offer(o);
                } else {
                    return this.parent.getPoolSize() < this.parent.getMaximumPoolSize() ? false : super.offer(o);
                }
            }
        }

        public boolean superOffer(Runnable o) {
            return super.offer(o);
        }
    }

    static class TaskThreadFactory implements ThreadFactory {
        final ThreadGroup group;
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String namePrefix;

        TaskThreadFactory(String namePrefix) {
            SecurityManager s = System.getSecurityManager();
            this.group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = namePrefix;
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement());
            t.setDaemon(true);
            if (t.getPriority() != 5) {
                t.setPriority(5);
            }
            return t;
        }
    }
}
