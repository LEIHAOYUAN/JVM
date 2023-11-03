package com.lei.jvm.spring.delay;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author leihaoyuan
 * @version 2023/11/3 9:48
 */
@Data
public class DelayData implements Delayed {


    private long delay;

    private long expire;

    private String sourceId;

    public DelayData(long delay,String sourceId){
        this.delay = delay;
        this.sourceId = sourceId;
        this.expire = System.currentTimeMillis() + this.delay;
    }


    public long getDelay(@NotNull TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NotNull Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }


}
