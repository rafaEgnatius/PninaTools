package org.thinpole;

public class MSet<T> 	{

	private Node firstNode = null;
	private Node lastNode = null;
	private int count = 0;
	
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		}
		return false;
	}
	
	public int size() {
		return count;
	}
	
	public T get(int index) {
		if (isEmpty() || index >= count) {
			return null;
		}
		Node workingNode = firstNode;
		for (int i = 0; i < index; i++) {
			workingNode = workingNode.next;
		}
		return workingNode.value;
	}

	public boolean add(T element) {
		
		if (isEmpty()) {
			firstNode = lastNode = new Node();
			firstNode.value = element;
			count++;
			return true;
		}
		
		Node workingNode = firstNode;
		for (int i = 0; i < count; i++) {
			if (workingNode.value.equals(element)) {
				return false;
			}
		}
		workingNode = new Node();
		workingNode.value = element;
		lastNode.next = workingNode;
		lastNode = workingNode;
		count++;
		return true;
	}

	class Node {
		private Node next;
		private T value;
	}
	
}
