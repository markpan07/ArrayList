package org.example.service;

import org.example.exception.ElementNotFoundException;
import org.example.exception.OutOfBoundException;
import org.example.exception.TooLittleArrayException;
import org.example.interfaces.StringList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringListImpl implements StringList {

    private String[] storage = new String[4];
    int pointer = 0;


    @Override
    public String add(String item) {
        if (pointer < storage.length - 1) {
            storage[pointer] = item;
            pointer++;
        } else {
            resize();
            storage[pointer] = item;
            pointer++;
        }
        return storage[pointer - 1];
    }


    @Override
    public String add(int index, String item) throws OutOfBoundException {
        if (index < 0 || index >= storage.length || storage[index] == null) {
            throw new OutOfBoundException("There is no element with such index");
        }
        if (storage[storage.length - 1] != null) {
            resize();
        }
        shiftToRight(index);
        storage[index] = item;
        pointer++;
        return storage[index];
    }

    @Override
    public String set(int index, String item) throws OutOfBoundException, NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= storage.length || storage[index] == null) {
            throw new OutOfBoundException("There is no element with such index");
        }
        storage[index] = item;
        return storage[index];
    }

    @Override
    public String remove(String item) {
        int index = indexOf(item);
        if(index == -1) {
            throw new ElementNotFoundException("There is no element with such index");
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= storage.length - 1 || storage[index] == null) {
            throw new ElementNotFoundException("There is no element with such index");
        }
        String del = storage[index];
        storage[index] = null;
        shiftToLeft(index);
        pointer--;
        return del;
    }

    @Override
    public boolean contains(String item) {
        if(indexOf(item) != -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int indexOf(String item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null && storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        for (int i = storage.length - 1; i >= 0; i--) {
            if (storage[i] != null && storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) throws ElementNotFoundException {
        if (index < 0 || index >= storage.length - 1 || storage[index] == null) {
            throw new ElementNotFoundException("There is no element with such index");
        }
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return false;
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {
        String[] newStore = new String[4];
        storage = newStore;
        pointer = 0;
    }

    @Override
    public String[] toArray() {
        String[] out = new String[storage.length];
        System.arraycopy(storage, 0, out, 0, storage.length);
        return out;
    }


    private void resize() {
        String[] storage2 = new String[(int) (storage.length * 1.5 + 1)];
        System.arraycopy(storage, 0, storage2, 0, storage.length);
        storage = storage2;
    }

    private void shiftToRight(int index) {
        if (storage[storage.length - 1] != null) {
            throw new TooLittleArrayException("Array has to be extended");
        }
        for (int i = storage.length - 2; i >= index; i--) {
            storage[i + 1] = storage[i];
        }
        storage[index] = null;
    }

    private void shiftToLeft(int index) {
        for (int i = index; i < storage.length - 1; i++) {
            storage[i] = storage[i + 1];
        }

    }

}
