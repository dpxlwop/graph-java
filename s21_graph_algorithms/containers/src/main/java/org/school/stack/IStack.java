package org.school.stack;

public interface IStack<T> {
    void push(T item);
    T pop();
    T top();
    boolean isEmpty();
}