package org.example.service;

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
    public String set(int index, String item) throws OutOfBoundException {
        if (index < 0 || index >= storage.length || storage[index] == null) {
            throw new OutOfBoundException("There is no element with such index");
        }
        storage[index] = item;
        return storage[index];
    }

    @Override
    public String remove(String item) {
        return null;
    }

    @Override
    public String remove(int index) {
        return null;
    }

    @Override
    public boolean contains(String item) {
        return false;
    }

    @Override
    public int indexOf(String item) {
        return 0;
    }

    @Override
    public int lastIndexOf(String item) {
        return 0;
    }

    @Override
    public String get(int index) {
        return null;
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

    public int getSizeArray() {
        return storage.length;
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

    public List<String> getAll(){
        return Collections.unmodifiableList(List.of(storage));
    }
}
