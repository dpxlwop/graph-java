package org.school.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class s21_Queue<T> implements IQueue<T>{
    private Deque<T> list;

    public s21_Queue() {
        list = new ArrayDeque<>();
    }

    public void push(T value){
        list.add(value);
    }

    public T pop(){
        if(list.isEmpty()){
            throw new RuntimeException("s21_Queue is empty");
        }
        return list.removeFirst();
    }

    public T front(){
        if(list.isEmpty()){
            throw new RuntimeException("s21_Queue is empty");
        }
        return list.getFirst();
    }

    public T back(){
        if(list.isEmpty()){
            throw new RuntimeException("s21_Queue is empty");
        }
        return list.getLast();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
}
