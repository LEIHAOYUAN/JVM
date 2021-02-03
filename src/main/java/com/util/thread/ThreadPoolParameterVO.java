package com.util.thread;

public class ThreadPoolParameterVO {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOLSIZE = 200;
    private static final int INITIAL_CAPACITY = 1000000;
    private static final int KEEP_ALIVE_TIME = 120;
    private Integer corePoolSize = 5;
    private Integer maximumPoolSize = 200;
    private Integer initialCapacity = 1000000;
    private Long keepAliveTime = 120L;
    private String threadName = "base-framework-threadPool-";
    private Boolean discard = true;

    public ThreadPoolParameterVO() {
    }

    public Boolean isDiscard() {
        return this.discard;
    }

    public void setDiscard(Boolean discard) {
        this.discard = discard;
    }

    public Integer getCorePoolSize() {
        return this.corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaximumPoolSize() {
        return this.maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Integer getInitialCapacity() {
        return this.initialCapacity;
    }

    public void setInitialCapacity(Integer initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public long getKeepAliveTime() {
        return this.keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String toString() {
        return "ThreadPoolParameterVO{corePoolSize=" + this.corePoolSize + ", maximumPoolSize=" + this.maximumPoolSize + ", initialCapacity=" + this.initialCapacity + ", keepAliveTime=" + this.keepAliveTime + ", threadName='" + this.threadName + '\'' + ", discard=" + this.discard + '}';
    }
}
