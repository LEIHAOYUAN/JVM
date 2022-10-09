package com.lei.jvm.stu;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * https://mp.weixin.qq.com/s/Z-RCDhZq6Ohf0iHA1cO5Jw
 */
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object element = elements[--size];
        elements[size] = null;
        //正确做法应该将无用的对象显示的设置为null
        return element;
    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}