package org.classes;

import org.list.List;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;
    public LinkedList() {
        size = 0;
        first = null;
        last = null;
    }
    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;Node(Node<E> prev, E element, Node<E> next) {
            this.data = element;
            this.prev = prev;
            this.next = next;
        }

    }


    @Override
    public boolean add(E element) {
        addLast(element);
        return true;
    }
    private E unlink(Node<E> node) {
        if (node == null) {
            return null;
        }
        final E element = node.data;
        final Node<E> prev = node.prev;
        final Node<E> next = node.next;
        if (prev == null) {
            first = next;
        }
        else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            last = prev;
        }
        else {
            next.prev = prev;
            node.next = null;
        }
        node.data = null;
        size--;
        return element;
    }

    public void addFirst(E element) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, element, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        }
        else {
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(E element) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        }
        else {
            l.next = newNode;
        }
        size++;
    }
    @Override
    public void add(int index, E element) {
        checkIndex(index);
        if (index == 0) {
            addFirst(element);
        }
        else if (size == index) {
            addLast(element);
        }
        else {
            int i = 0;
            Node<E> current = findNode(index);
            Node<E> p = current.prev;
            Node<E> newNode  = new Node<>(p, element, current);
            current.prev = newNode;
            p.next = newNode;
            size++;
        }
        
    }
    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + "size: " + size);
        }
    }
    public boolean removeLast() {
        return unlink(last) != null;
    }
    @Override
    public boolean remove(Object o) {
        return unlink(findNode(indexOf(o))) != null;
    }
    public E removeFirst() {
        return unlink(first);
    }
    @Override
    public E remove(int index) {
        checkIndex(index);
        return unlink(findNode(index));
    }

    private Node<E> findNode(int index) {
        if (index == -1) {
            throw new NoSuchElementException();
        }
        Node<E> cur = first;
        for(int i = 0; i < index; i++){
            cur = cur.next;
        }
        return cur;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return findNode(index).data;
    }

    public E getLast() {
        return last.data;
    }
    public E getFist() {
        return last.data;
    }
    @Override
    public int indexOf(Object o) {
        int i = 0;
        Node<E> cur = first;
        while (cur != null) {
            if (o.equals(cur.data)) {
                return i;
            }
            cur = cur.next;
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int i = size - 1;
        Node<E> cur = last;
        while (cur != null && !o.equals(cur.data)) {
            cur = cur.prev;
            i--;
        }
        if (cur != null) {
            return i;
        }
        return -1;
    }

    @Override
    public List<E> sublist(int fromIndex, int ToIndex) {
        checkIndex(fromIndex);
        checkIndex(ToIndex);
        if (fromIndex > ToIndex) {
            throw new IndexOutOfBoundsException();
        }
        List<E> eList = new LinkedList<>();
        for ( ; fromIndex < ToIndex; fromIndex++) {
            eList.add(findNode(fromIndex).data);
        }
        return eList;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E>current = first;
        int i = 0;
        while (current != null) {
            array[i] = current.data;
            current = current.next;
            i++;
        }
        return array;
    }
}

