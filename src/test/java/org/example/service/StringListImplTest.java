package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.exception.OutOfBoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    private StringListImpl out = new StringListImpl();

    @BeforeEach
    void setUp() {
        out.add("String1");
        out.add("String2");
    }
/*    @AfterEach
    void after(){

    }*/

    @Test
    void addInTheEnd() {
        out.add("String3");
        out.add("String4");
        out.add("String5");
        String[] expected = new String[]{"String1", "String2", "String3", "String4", "String5", null, null};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out);
    }

    @Test
    void addByIndex() {
        out.add(1, "newString");
        String[] expected = new String[]{"String1", "newString", "String2", null};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out);
    }

    @Test
    void addByIndexWithArrayExtending() {
        out.add("String3");
        out.add("String4");
        out.add(2, "newString");
        String[] expected = new String[]{"String1", "newString", "String3", "String4", null, null, null};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out);
    }

    @Test
    void addByIncorrectIndex() {
        Assertions.assertThatExceptionOfType(OutOfBoundException.class).isThrownBy(() ->
                out.add(3, "newString"));
        Assertions.assertThatExceptionOfType(OutOfBoundException.class).isThrownBy(() ->
                out.add(5, "newString"));
    }


    @Test
    void set() {
    }

    @Test
    void remove() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void contains() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void get() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void clear() {
    }

    @Test
    void toArray() {
    }
}