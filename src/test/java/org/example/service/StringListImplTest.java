package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.exception.ElementNotFoundException;
import org.example.exception.OutOfBoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    private StringListImpl out = new StringListImpl(4);

    @BeforeEach
    void setUp() {
        out.add("String1");
        out.add("String2");
    }
    @AfterEach
    void after(){
        out.clear();
    }

    @Test
    void addInTheEnd() {
        out.add("String3");
        out.add("String4");
        out.add("String5");
        String[] expected = new String[]{"String1", "String2", "String3", "String4", "String5", null, null};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
    }

    @Test
    void addByIndex() {
        out.add(1, "newString");
        String[] expected = new String[]{"String1", "newString", "String2", null};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
    }

    @Test
    void addByIndexWithArrayExtending() {
        out.add("String3");
        out.add("String4");
        out.add(2, "newString");
        String[] expected = new String[]{"String1", "String2", "newString", "String3", "String4", null, null};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
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
        out.set(1, "newString");
        String[] expected = new String[]{"String1", "newString", null, null};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
    }

    @Test
    void removeByIndex() {
        String[] expected = new String[]{"String2", null, null, null};
        out.remove(0);
        Assertions.assertThat(expected).
                isEqualTo(out.toArray());
    }

    @Test
    void removeByIndexNull() {
        Assertions.assertThatExceptionOfType(NullPointerException.class).
                isThrownBy(() ->
                        out.remove(null));
    }

    @Test
    void removeByIncorrectIndex() {
        Assertions.assertThatExceptionOfType(ElementNotFoundException.class).
                isThrownBy(() ->
                        out.remove(3));
    }

    @Test
    void removeByItem() {
        out.add("String3");
        out.add("String4");
        String[] expected = new String[]{"String1", "String2", "String4", null, null, null, null};
        out.remove("String3");
        Assertions.assertThat(expected).
                isEqualTo(out.toArray());
    }

    @Test
    void removeNonExistedItem() {
        Assertions.assertThatExceptionOfType(ElementNotFoundException.class).
                isThrownBy(() ->
                        out.remove("sdfdsfs"));
    }

    @Test
    void contains() {
        out.add("String3");
        out.add("String4");
        out.add("String5");
        Assertions.assertThat(out.contains("String1")).isTrue();
        Assertions.assertThat(out.contains("Str")).isFalse();

    }

    @Test
    void indexOf() {
        int expected = 0;
        Assertions.assertThat(expected).
                isEqualTo(out.indexOf("String1"));
        int expected2 = -1;
        Assertions.assertThat(expected2).
                isEqualTo(out.indexOf("Stri"));
    }

    @Test
    void indexOfNull() {
        Assertions.assertThatExceptionOfType(NullPointerException.class).
                isThrownBy(() -> out.indexOf(null));

    }

    @Test
    void lastIndexOfNull() {
        Assertions.assertThatExceptionOfType(NullPointerException.class).
                isThrownBy(() -> out.lastIndexOf(null));
    }

    @Test
    void lastIndexOf() {
        int expected = 0;
        Assertions.assertThat(expected).
                isEqualTo(out.lastIndexOf("String1"));
        int expected2 = -1;
        Assertions.assertThat(expected2).
                isEqualTo(out.lastIndexOf("Stri"));
    }

    @Test
    void get() {
        String expected = "String1";
        Assertions.assertThat(expected).
                isEqualTo(out.get(0));
    }

    @Test
    void testEquals() {
    }

    @Test
    void size() {
        Assertions.assertThat(out.size()).
                isEqualTo(2);
        out.add("String3");
        out.add("String4");
        out.add("String5");
        Assertions.assertThat(out.size()).
                isEqualTo(5);
    }

    @Test
    void isEmpty() {
    }

    @Test
    void clear() {
        out.clear();
        String[] expected = new String[]{null, null, null, null};
        Assertions.assertThat(expected).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
        out.add("String1");
        out.add("String2");
        String[] expected2 = new String[]{"String1", "String2", null, null};
        Assertions.assertThat(expected2).
                usingRecursiveComparison().
                isEqualTo(out.toArray());
    }

    @Test
    void toArray() {
    }
}