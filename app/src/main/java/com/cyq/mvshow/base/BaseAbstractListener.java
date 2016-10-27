package com.cyq.mvshow.base;

/**
 * Created by win7 on 2016/8/13.
 */
public abstract class BaseAbstractListener<T, S> implements BaseInterfaceListener<T, S> {
    @Override
    public void start() {

    }

    @Override
    public void success(T o) {

    }

    @Override
    public void fail(S o) {
    }

    @Override
    public void cancel() {

    }

    @Override
    public void complete() {

    }
}
