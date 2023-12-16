package org.classes;

import jdk.jshell.Snippet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.list.List;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static  org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private LinkedList<String> list;
    private final String a = "Bolot";
    private final String b = "Kubat";
    private final String c = "Asema";
    private final String d = "Kamila";
    @BeforeEach
    public void init() {
        list = new LinkedList<>();
        list.add(c);
        list.add(d);
    }

    @Test
    void addTest() {
        assertTrue(list.add(b));
        assertTrue(list.add(a));
        assertEquals(4, list.size());
    }
    @Test
    void addFirstTest() {
        list.addFirst(a);
        assertEquals(a, list.get(0));
        list.addFirst(b);
        assertEquals(b, list.get(0));
    }
    @Test
    void addLastTest() {
        list.addLast(a);
        assertEquals(a, list.get(list.size() - 1));
        list.addLast(b);
        assertEquals(b, list.get(list.size() - 1));
    }
    @Test
    void add() {
        String check;
        int limit = list.size();
        for (int i = 0; i < limit; i++) {
            check = "check" + i;
            list.add(i, check);
            assertEquals(check, list.get(i));
        }
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(list.size() + 1, "sd");
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(list.size() + 10, "sd");
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(-1, "sd");
        });
    }

    @Test
    void removeLastTest() {
        assertTrue(list.removeLast());
        assertEquals(list.getLast(), c);
        list.removeLast();
        assertFalse(list.removeLast());
    }

    @Test
    void removeTest() {
        String test1 = "school21";
        String test2 = "Kazan";
        assertTrue(list.add(test2));
        assertTrue(list.add(test1));
        assertTrue(list.remove("school21"));
        assertTrue(list.remove("Kazan"));
        assertThrows(NoSuchElementException.class, () -> {
            list.remove("Bishlkek");
        });
        //System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    void removeFirstTest() {
        assertEquals(list.removeFirst(), c);
        assertEquals(list.removeFirst(), d);
        assertNull(list.removeFirst());
    }
    @Test
    void removeIndexTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(10);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });
        assertEquals(c, list.remove(0));
        assertEquals(d, list.remove(0));
    }
}
