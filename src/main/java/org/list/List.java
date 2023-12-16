package org.list;

public interface List <E>{
    boolean add(E element);
    void add(int index, E element);
    boolean remove(Object o);
    E remove(int index);
    E get(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    List<E> sublist(int fromIndex, int ToIndex);
    boolean contains(Object o);
    E set(int index, E element);
    int size();
    Object[] toArray();
}
