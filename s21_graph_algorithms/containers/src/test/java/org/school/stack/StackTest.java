package org.school.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void pushAndTopTest() {
        s21_Stack<Integer> s21Stack = new s21_Stack<>();

        s21Stack.push(1);
        s21Stack.push(2);

        assertEquals(2, s21Stack.top());
    }

    @Test
    void popTest() {
        s21_Stack<Integer> s21Stack = new s21_Stack<>();

        s21Stack.push(1);
        s21Stack.push(2);

        assertEquals(2, s21Stack.pop());
        assertEquals(1, s21Stack.pop());
    }

    @Test
    void isEmptyTest() {
        s21_Stack<Integer> s21Stack = new s21_Stack<>();

        assertTrue(s21Stack.isEmpty());

        s21Stack.push(1);

        assertFalse(s21Stack.isEmpty());
    }

    @Test
    void popFromEmptyStackTest() {
        s21_Stack<Integer> s21Stack = new s21_Stack<>();

        assertThrows(RuntimeException.class, s21Stack::pop);
    }

    @Test
    void topFromEmptyStackTest() {
        s21_Stack<Integer> s21Stack = new s21_Stack<>();

        assertThrows(RuntimeException.class, s21Stack::top);
    }
}