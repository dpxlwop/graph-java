package org.school.queue;

public interface IQueue<T> {
    void push(T value);
    T pop();
    T front();
    T back();
    boolean isEmpty();
}