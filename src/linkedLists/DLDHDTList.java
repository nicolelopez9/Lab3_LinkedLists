package linkedLists;

import java.util.NoSuchElementException;

public class DLDHDTList<E> extends AbstractDLList<E>{
	private DNode<E> header, trailer; 
	private int length; 

	public DLDHDTList() { 
		header = new DNode<E>();
		trailer = new DNode<E>(null, header, null);
		header.setNext(trailer);
		this.length = 0;
	}

	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}

	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		DNode<E> temporaryNode = (DNode<E>)target;
		DNode<E> newNode = (DNode<E>)nuevo;
		DNode<E> nodeBefore = (DNode<E>)temporaryNode.getPrev();
		newNode.setPrev(nodeBefore);
		newNode.setNext(temporaryNode);
		temporaryNode.setPrev(newNode);
		nodeBefore.setNext(newNode);
		this.length++;
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		DNode<E> dTarget  = (DNode<E>)target;
		if(dTarget.getNext()==trailer)
			throw new NoSuchElementException("Target is the last element of the list");

		return(Node<E>)dTarget.getNext();
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		DNode<E> dTarget  = (DNode<E>)target;
		if(dTarget.getPrev()==header)
			throw new NoSuchElementException("Target is the first element of the list");

		return(Node<E>)dTarget.getPrev();
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		DNode<E> primerNodo = (DNode<E>)getFirstNode();
		DNode<E> nodoDespPrimero =  primerNodo.getNext();
		header.setNext(nodoDespPrimero);
		nodoDespPrimero.setPrev(header);
		this.length --;
		primerNodo.clean();


		return; }

	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}

	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		DNode<E> nodo = (DNode<E>) getFirstNode(); 
		while(this.length > 0){
			removeNode(nodo);
			nodo = header.getNext();
		}
	}

	protected void finalize() throws Throwable {
		try {
			this.destroy(); 
		} finally {
			super.finalize();
		}
	}

}
