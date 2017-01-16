package org.tpvp2k.algotrading.dto;

public class DoublyLinkedList <E>{
	private Node head;
	private Node tail;
	private int size;
	public DoublyLinkedList() {
		size = 0;
	}
	private class Node {
		E element;
		 Node next;
	        Node prev;
	 
	        public Node(E element, Node next, Node prev) {
	            this.element = element;
	            this.next = next;
	            this.prev = prev;
	        }
	}
	 /**
     * returns the size of the linked list
     * @return
     */
    public int size() { return size; }
     
    /**
     * return whether the list is empty or not
     * @return
     */
    public boolean isEmpty() { return size == 0; }
    /**
     * adds element at the starting of the linked list
     * @param element
     */
    public void addFirst(E element) {
        Node tmp = new Node(element, head, null);
        if(head != null ) {head.prev = tmp;}
        head = tmp;
        if(tail == null) { tail = tmp;}
        size++;
        System.out.println("adding: "+element);
    }
    
    /**
     * adds element at the end of the linked list
     * @param element
     */
    public void addLast(E element) {
         
        Node tmp = new Node(element, null, tail);
        if(tail != null) {tail.next = tmp;}
        tail = tmp;
        if(head == null) { head = tmp;}
        size++;
        System.out.println("adding: "+element);
    }
    /**
     * this method walks forward through the linked list
     */
    public void iterateForward(){
         
        System.out.println("iterating forward..");
        Node tmp = head;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.next;
        }
    }
     
    /**
     * this method walks backward through the linked list
     */
    public void iterateBackward(){
         
        System.out.println("iterating backword..");
        Node tmp = tail;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.prev;
        }
    }
     
}
