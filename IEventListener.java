package me.bigua.comiccollector;

/**
 * Created by Bigua on 3/23/15.
 * bigua.kun@gmail.com
 */
public interface IEventListener<T> {
    void onEventFired(T result);
}