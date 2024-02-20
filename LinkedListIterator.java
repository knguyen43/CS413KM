/*************************************************
File: LinkedListIterator.java
By: Khang Nguyen
Date: Feb 19 2024
Compile: javac LinkedListIterator.java
Usage: java LinkedListIterator
System: Any system with Java support
Description: This program demonstrates a customized iterator for a linked implementation of an Abstract Data Type (ADT) List. It includes classes representing the ADT List, a linked list with iterator, and a main class to test the implementation.
*************************************************/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Define ListInterface
interface ListInterface<E> {
    // Adds a new entry to the end of this list.
    // The list size is increased by 1.
    void add(E newEntry);

    // Adds a new entry at the specified position in this list.
    boolean add(int newPosition, E newEntry);

    // Removes the entry at the given position from this list.
    E remove(int givenPosition);

    // Removes all entries from this list.
    void clear();

    // Replaces the entry at the given position in this list with the specified entry.
    E replace(int givenPosition, E newEntry);

    // Retrieves the entry at the given position in this list.
    E getEntry(int givenPosition);

    // Retrieves all entries that are in this list in the order in which they occur.
    E[] toArray();

    // Determines whether this list contains a given entry.
    boolean contains(E anEntry);

    // Gets the length of this list.
    int getLength();

    // Determines whether this list is empty.
    boolean isEmpty();

    // Returns an iterator over elements of type E.
    Iterator<E> getIterator();
}

// MyLList class implementing ListInterface
class MyLList<E> implements ListInterface<E> {
    private Node head; // Reference to the first node
    private int size; // Number of elements in the list

    // Constructor
    public MyLList() {
        head = null;
        size = 0;
    }

    // Inner class for the nodes
    private class Node {
        private E data;
        private Node next;

        public Node(E dataPortion) {
            this(dataPortion, null);
        }

        public Node(E dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }

    // Implement methods of ListInterface
    // Add method
    public void add(E newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Add method at a specified position
    public boolean add(int newPosition, E newEntry) {
        if (newPosition < 1 || newPosition > size + 1) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        Node newNode = new Node(newEntry);
        if (newPosition == 1) {
            newNode.next = head;
            head = newNode;
        } else {
            Node previous = getNodeAt(newPosition - 1);
            newNode.next = previous.next;
            previous.next = newNode;
        }
        size++;
        return true;
    }

    // Remove method
    public E remove(int givenPosition) {
        if (givenPosition < 1 || givenPosition > size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        E removedItem;
        if (givenPosition == 1) {
            removedItem = head.data;
            head = head.next;
        } else {
            Node previous = getNodeAt(givenPosition - 1);
            Node current = previous.next;
            removedItem = current.data;
            previous.next = current.next;
        }
        size--;
        return removedItem;
    }

    // Clear method
    public void clear() {
        head = null;
        size = 0;
    }

    // Replace method
    public E replace(int givenPosition, E newEntry) {
        if (givenPosition < 1 || givenPosition > size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        Node node = getNodeAt(givenPosition);
        E originalData = node.data;
        node.data = newEntry;
        return originalData;
    }

    // Get entry method
    public E getEntry(int givenPosition) {
        if (givenPosition < 1 || givenPosition > size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        Node node = getNodeAt(givenPosition);
        return node.data;
    }

    // To array method
    @SuppressWarnings("unchecked") // Suppress warning about unchecked cast
    public E[] toArray() {
        ArrayList<E> resultList = new ArrayList<>();
        Node current = head;
        while (current != null) {
            resultList.add(current.data);
            current = current.next;
        }
        return resultList.toArray((E[]) new Object[resultList.size()]);
    }

    // Contains method
    public boolean contains(E anEntry) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(anEntry)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Get length method
    public int getLength() {
        return size;
    }

    // Is empty method
    public boolean isEmpty() {
        return size == 0;
    }

    // Helper method to get the node at a specified position
    private Node getNodeAt(int givenPosition) {
        if (givenPosition < 1 || givenPosition > size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        Node current = head;
        for (int i = 1; i < givenPosition; i++) {
            current = current.next;
        }
        return current;
    }

    // Returns an iterator over elements of type E.
    public Iterator<E> getIterator() {
        return new MyListIterator();
    }

    // Inner class for the iterator
    private class MyListIterator implements Iterator<E> {
        private Node current;

        public MyListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = current.data;
            current = current.next;
            return data;
        }
    }
}

// LinkedListWithIterator class
class LinkedListWithIterator<E> extends MyLList<E> {
    // Nothing needs to be added here as it simply extends MyLList
}

public class LinkedListIterator {
    public static void main(String[] args) {
        // Create an instance of MyLList and populate it with strings
        MyLList<String> myList = new MyLList<>();
        myList.add("Hello");
        myList.add("World");
        myList.add("!");

        // Get the iterator object for the instance
        Iterator<String> iterator = myList.getIterator();

        // Use the Iterator methods to iterate and print objects in the list
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
