package com.pluralsight.algo.deques;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class LinkedList<Item> implements Iterable<Item> {

        private class Node<Item> {
            private Item item;
            private Node<Item> next;

            Node(Item item) {
                this.item = item;
                this.next = null;
            }

            public Item getItem() {
                return item;
            }

            public void setItem(Item item) {
                this.item = item;
            }

            public Node<Item> getNext() {
                return next;
            }

            public void setNext(Node<Item> next) {
                this.next = next;
            }
        }


        private Node<Item> head;
        private int numNodes;

        public LinkedList(Item item) {
            head = new Node<>(item);
            numNodes++;
        }

        public LinkedList() {
        }

        public Node<Item> addAtHead(Item item) {

            if (head != null) {
                Node<Item> current = head;
                head = new Node<>(item);
                head.setNext(current);
            } else {
                head = new Node<>(item);
            }

            numNodes++;

            return head;
        }

        public Node<Item> addAtItemail(Item item) {

            Node<Item> tail;

            if (head != null) {
                tail = head;

                while (tail.getNext() != null) {
                    tail = tail.getNext();
                }

                tail.setNext(new Node<>(item));

            } else {
                tail = new Node<>(item);
                head = tail;
            }
            numNodes++;

            return tail;
        }

        public void addAtIndex(Item item, int index) {
            Node<Item> current = head;
            Node<Item> holder;

            for (int i = 0; i < index - 1 && current.getNext() != null; i++) {
                current = current.getNext();
            }
            holder = current.getNext();
            current.setNext(new Node<>(item));
            current.getNext().setNext(holder);
            numNodes++;
        }

        public Item removeAtHead() {
            Node<Item> toBeRemoved = head;
            head = head.getNext();
            numNodes--;
            return toBeRemoved.item;
        }


        public Item removeAtTAil() {
            Node<Item> current = head;
            Node<Item> toBeRemoved;

            while (current.getNext() != null) {
                current = current.getNext();
            }
            toBeRemoved = current;
            numNodes--;
            return toBeRemoved.item;
        }

        public Item deleteAtIndex(int index) {
            Node<Item> current = head;

            for (int i = 0; i < index - 1 && current.getNext() != null; i++) {
                current = current.getNext();
            }

            current.setNext(current.getNext().getNext());
            numNodes--;
            return current.item;
        }

        public int findIndex(Item nodeItem) {
            Node<Item> current = head;
            int index = 0;

            while (current != null && !current.equals(nodeItem)) {
                index++;
                current = current.getNext();
            }

            return index;
        }

        public Item find(int index) {

            Node<Item> current = head;

            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getItem();
        }


        public Item first() {
            return head.getItem();
        }

        public Item last() {

            Node<Item> last = head;

            while (last.getNext() != null) {
                last = last.getNext();
            }

            return last.getItem();
        }



        public int size() {
            return numNodes;
        }

        @Override
        public Iterator<Item> iterator() {
            Iterator<Item> iterator = new Iterator<Item>() {
                private Node<Item> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public Item next() {
                    if (current == null) {
                        throw new NoSuchElementException();
                    }

                    Item tmp = current.getItem();
                    current = current.getNext();
                    return tmp;
                }
            };
            return iterator;
        }
    }

    private LinkedList<Item> list;

    public Deque() {
        list = new LinkedList<>();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        list.addAtHead(item);
    }

    public void addLast(Item item) {
        list.addAtItemail(item);
    }

    public Item removeFirst() {
        return list.removeAtHead();
    }

    public Item removeLast(){
        return list.removeAtTAil();
    }

    @Override
    public Iterator<Item> iterator() {
        return list.iterator();
    }
}
