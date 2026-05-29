package org.school.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class s21_Stack<T> implements IStack<T> {
    private Deque<T> list;

    public s21_Stack() {
        list = new ArrayDeque<>();
    }

    public void push(T item) {
        list.add(item);
    }

    public T pop() {
        if (list.isEmpty()) {
            throw new RuntimeException("s21_Stack is empty");
        }
        return list.removeLast();
    }

    public T top() {
        if (list.isEmpty()) {
            throw new RuntimeException("s21_Stack is empty");
        }
        return list.getLast();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
