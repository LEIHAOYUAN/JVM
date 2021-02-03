package com.util.thread;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorExtend extends ThreadPoolExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorExtend.class);
    private static final String THREAD_POOL_NAME = "base-common-threadPool-";
    private String threadPoolName;
    private static AtomicInteger newNumber = new AtomicInteger();
    final AtomicInteger submittedTasksCount = new AtomicInteger();
    private long preCompletedTaskCount = -1L;
    private long lastCalTime = -1L;

    ThreadPoolExecutorExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        newNumber.getAndIncrement();
        //Zeus.getStatusInstall().register(this);
    }

    public AtomicInteger getSubmittedTasksCount() {
        return this.submittedTasksCount;
    }

    public void execute(Runnable command) {
        this.submittedTasksCount.incrementAndGet();
        super.execute(command);
    }

    protected void afterExecute(Runnable r, Throwable t) {
        this.submittedTasksCount.decrementAndGet();
        if (r instanceof CommonFutureTask) {
            IAsynchronousHandler handler = ((CommonFutureTask) r).getR();
            if (handler == null) {
                throw new NullPointerException("线程池参数对象为null!");
            }

            handler.executeAfter(t);
        }

    }

    protected void beforeExecute(Thread t, Runnable r) {
        if (r instanceof CommonFutureTask) {
            IAsynchronousHandler handler = ((CommonFutureTask) r).getR();
            if (handler == null) {
                throw new NullPointerException("线程池参数对象为null!");
            }

            handler.executeBefore(t);
        }

    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new CommonFutureTask(callable);
    }

    public String toString() {
        return "ThreadPoolExecutor: ActiveCount = " + this.getActiveCount() + " CompletedTaskCount = " + this.getCompletedTaskCount() + " CorePoolSize = " + this.getCorePoolSize() + " LargestPoolSize = " + this.getLargestPoolSize() + " MaximumPoolSize = " + this.getMaximumPoolSize() + " PoolSize = " + this.getPoolSize() + " queueSize = " + this.getQueue().size() + " queueString=[" + this.getQueue().toString() + "]";
    }

    public String getId() {
        return "ThreadPool";
    }

    public String getDescription() {
        return "ThreadPool info";
    }

    public Map<String, String> getProperties() {
        Map<String, String> map = new HashMap(10);
        if ("base-framework-threadPool-".equals(this.threadPoolName)) {
            return map;
        } else {
            try {
                map.put(this.threadPoolName + "_submittedTasksCount", String.valueOf(this.getCompletedTasksRecently()));
                map.put(this.threadPoolName + "_activeCount", String.valueOf(super.getActiveCount()));
                map.put(this.threadPoolName + "_poolsize", String.valueOf(super.getPoolSize()));
                map.put(this.threadPoolName + "_maxPoolsize", String.valueOf(super.getMaximumPoolSize()));
                map.put(this.threadPoolName + "_queuesize", String.valueOf(super.getQueue().size()));
                return map;
            } catch (Exception var3) {
                logger.error(var3.getMessage());
                return map;
            }
        }
    }

    public void setThreadPoolName(String threadPoolName) {
        if (StringUtils.isEmpty(threadPoolName)) {
            threadPoolName = "ThreadPoolExecutorExtend_" + newNumber.get();
        }

        this.threadPoolName = threadPoolName;
    }

    private long getCompletedTasksRecently() {
        if (this.preCompletedTaskCount == -1L) {
            this.preCompletedTaskCount = super.getCompletedTaskCount();
            this.lastCalTime = System.currentTimeMillis();
            return 0L;
        } else {
            long curCompletedTaskCount = super.getCompletedTaskCount();
            long count = curCompletedTaskCount - this.preCompletedTaskCount;
            long curCalTime = System.currentTimeMillis();
            long delta = curCalTime - this.lastCalTime;
            this.preCompletedTaskCount = curCompletedTaskCount;
            this.lastCalTime = curCalTime;
            return count * 1000L / delta;
        }
    }
}