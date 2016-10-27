package com.cyq.mvshow.base;

/**
 * Created by win7 on 2016/8/13.
 */
public interface BaseInterfaceListener<T, S> {
    public void start();

    public void success(T t);

    public void fail(S s);

    public void cancel();

    public void complete();
}
