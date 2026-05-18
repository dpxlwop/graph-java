package org.school.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void pushAndFrontTest() {
        s21_Queue<Integer> s21Queue = new s21_Queue<>();

        s21Queue.push(1);
        s21Queue.push(2);

        assertEquals(1, s21Queue.front());
    }

    @Test
    void backTest() {
        s21_Queue<Integer> s21Queue = new s21_Queue<>();

        s21Queue.push(1);
        s21Queue.push(2);

        assertEquals(2, s21Queue.back());
    }

    @Test
    void popTest() {
        s21_Queue<Integer> s21Queue = new s21_Queue<>();

        s21Queue.push(1);
        s21Queue.push(2);

        assertEquals(1, s21Queue.pop());
        assertEquals(2, s21Queue.pop());
    }

    @Test
    void isEmptyTest() {
        s21_Queue<Integer> s21Queue = new s21_Queue<>();

        assertTrue(s21Queue.isEmpty());

        s21Queue.push(1);

        assertFalse(s21Queue.isEmpty());
    }

    @Test
    void popFromEmptyQueueTest() {
        s21_Queue<Integer> s21Queue = new s21_Queue<>();

        assertThrows(RuntimeException.class, s21Queue::pop);
    }

    @Test
    void frontFromEmptyQueueTest() {
        s21_Queue<Integer> s21Queue = new s21_Queue<>();

        assertThrows(RuntimeException.class, s21Queue::front);
    }

    @Test
    void backFromEmptyQueueTest() {
        s21_Queue<Integer> s21Queue = new s21_Queue<>();

        assertThrows(RuntimeException.class, s21Queue::back);
    }
}