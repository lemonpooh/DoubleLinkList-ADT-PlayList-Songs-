 
package adt; 
import adt.DoublyNode;
import java.util.NoSuchElementException; 
/** 
 * @author 
 */
public class DoublyLinkedList<T> implements DoublyLinkedList_Interface<T>{
        private DoublyNode<T> iteratorPointer;
	private DoublyNode<T> reverseIteratorPointer;
	int size; 
        protected DoublyNode<T> root, trailer, lastAccessed;    
        
                     /**     Getter      **/
        /**
         * Returns the Doubly Node at given index
         * @param index
         * @return
         */
        @Override
        public DoublyNode<T> getNode(int index) {// Returns the Doubly Node at given index  
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("Index not available.");
        }
        // If index=0 , return head
        if (index == 0) {
            return this.root;
        }
        // If index= size, return last node
        if (index == this.size - 1) {
            return getLastNode(this.root);
        }
        int pointer = 0;
        DoublyNode<T> pointerNode = this.root;
        while (pointer <= index) {
            if (pointer == index) {
                return pointerNode;
            } else {
                pointerNode = pointerNode.getNext();
                pointer++;
            }
        }
        return null;
        }
         
        public T get(int givenPosition) {//return the data object from the given position
          T result = null; 
          if ((givenPosition >= 1) && (givenPosition <= size)) {
            DoublyNode currentNode = root;
            for (int i = 0; i < givenPosition - 1; ++i) {
              currentNode = currentNode.getNext(); // advance currentNode to next node
            }
            result = (T) currentNode.getData();	// currentNode is pointing to the node at givenPosition
          } 
          return result;
        }
        
	/**
	 * Returns last node of given doubly node
	 * @param node
	 * @return
	  */
        @Override
	public DoublyNode<T> getLastNode(DoublyNode<T> node) {// Returns last node of given doubly node
	      if(node !=null) {
			DoublyNode<T> lastNode = node;
			if (lastNode.getNext() != null) {
				return getLastNode(lastNode.getNext());
			} else {
				return lastNode;
			}
	      }
	      return null;
        } 

        public DoublyNode<T> getIteratorPointer() { // Returns reverseIteratorPointer
            return iteratorPointer;
        }
        public DoublyNode<T> getReverseIteratorPointer() { 
            return reverseIteratorPointer;
	}

                        /**     Setter      **/
        public void setIteratorPointer(DoublyNode<T> iteratorPointer) {
            this.iteratorPointer = iteratorPointer;
        }
        /******************************************************/
        /*           get iterator current node                */
        /******************************************************/  
        @Override
        public boolean hasPrevious() {   // check if node has previous node
            if(iteratorPointer==null){
                iteratorPointer=this.reverseIteratorPointer;
            }
            if(iteratorPointer.getPrevious()!=null) {  //2  
                    System.out.println("Last Access - "+lastAccessed);
                    return true; 
            }
            return false;
        } 
        
        @Override
	public T previous() {// return previous node data
            T data; 
            iteratorPointer=lastAccessed;
            data = iteratorPointer.getPrevious().getData(); 
            iteratorPointer=iteratorPointer.getPrevious();
            lastAccessed=iteratorPointer;//0 
            return data;
	}   
        
        @Override
	public boolean hasNext() {//check if node has next node
            if(iteratorPointer==null) {
                    return false;
            }
            if(iteratorPointer!=null) {//2
                    return true;
            }
            return false;
	}  
        
        @Override
	public T next() {  //return next node data
            lastAccessed=iteratorPointer;//1
            T data=iteratorPointer.getData();
            iteratorPointer=iteratorPointer.getNext();//2
            return data;
        } 
	 
	private void resetIteratorPointers() {//reset iteratorPointer and reverseIteratorPointer
            iteratorPointer=this.root;
            reverseIteratorPointer=getLastNode(this.root);
	} 
         
        
        /************************************************** 
         * @param reverse 
         */
        @Override
	public void reverse(boolean recursive) {//reverse the double linklist node
            if(this.root==null) {
                    return;
            }
            if(!recursive) {
                    DoublyNode<T> current=this.root;
                    DoublyNode<T> next=null;
                    DoublyNode<T> previous=null;
                    while(current!=null) {
                            next=current.getNext();
                            previous=current.getPrevious();
                            //swip next with previous and previous with next
                            current.setNext(previous);
                            current.setPrevious(next);
                            current=current.getPrevious();
                    }
                    this.root=previous.getPrevious();
            }
            else {
                    this.root=reverseByRecursion(this.root);
            }
            resetIteratorPointers();
	}
     
        private DoublyNode<T> reverseByRecursion(DoublyNode<T> currentNode) {//reverse the linklist node by using recursion method
            DoublyNode<T> current=currentNode;
            DoublyNode<T> next=null;
            DoublyNode<T> previous=null;
            if(current!=null) {
                    next=current.getNext();
                    previous=current.getPrevious();
                    //swip next with previous and previous with next
                    current.setNext(previous);
                    current.setPrevious(next);
                    current=current.getPrevious();
                    if(current!=null) {
                    return reverseByRecursion(current);
                    }
                    else {
                    return previous.getPrevious();
                    }
            }
            return null;
            } 
        /*******************************************/
        /****             add                      */
        /*******************************************/ 
        @Override
	public void add(T data) { //add element as last node
            //cannot insert null data to newnode
            if (data == null) {
                    return;
            }
            //create first node
            //point root to 1st node
            if (root == null) {
                    root = new DoublyNode<T>(data);
            } else {
                    //create newnode
                    DoublyNode<T> newNode = new DoublyNode<T>(data);
                    //create copy of lastnode
                    DoublyNode<T> lastNode = getLastNode(root); 
                    lastNode.setNext(newNode);// ]]->
                    newNode.setPrevious(lastNode);// <-[[
            }
            size++; 
	}
        
        /**
	 * Add element at first. 
	 * @param data
	 */
        @Override
	public void addAtFirst(T data) {//add element as first node
		if (data == null) {
			return;
		}
		DoublyNode<T> newNode = new DoublyNode<T>(data);
		if (this.root != null) {
			this.root.setPrevious(newNode);
			newNode.setNext(this.root);
			this.root = newNode;
		} else {
			this.root = newNode;
		}
		size++;
		resetIteratorPointers();
	}
        /**
	 * Add the element at specified index. Index start from 0 to n-1 where n=size of
	 * linked list. If index is negative value, nothing will be added to linked
	 * list.
	 * 
	 * if index =0 , element will be added at head and element become the first
	 * node.
	 * 
	 * @param data
	 * @param index
	 *            - index at which element to be added.
	 */
        @Override
	public void add(T data, int index) throws IndexOutOfBoundsException {// Add the element at specified index. Index start from 0 to n-1
            if (data == null) {
                    return;
            }
            // If index=0 , we should add the data at head
            if (index == 0) {
                    addAtFirst(data);
                    return;
            }
            // If index= size, we should add the data at last
            if (index == this.size) {
                    add(data);
            } else if (index < this.size) {
                    //create newnode & chain it
                    DoublyNode<T> newNode = new DoublyNode<T>(data);
                    // get the node at (index) from linked list and mark as rightNode.
                    // get the node at (index-1) from linked list and mark as leftNode.
                    // set node of newly created node as right node.
                    // set node of left node as newly created Node.
                    DoublyNode<T> rightNode = getNode(index);
                    DoublyNode<T> leftNode = rightNode.getPrevious();
                    leftNode.setNext(newNode);
                    newNode.setPrevious(leftNode);
                    newNode.setNext(rightNode);
                    rightNode.setPrevious(newNode);
                    size++;
            } else {
                    throw new IndexOutOfBoundsException("Index not available.");
            } 
	}
         
	/**
	 * Delete the data from first occurrence
	 * @param data
	 */
        @Override
	public void delete(T data) {
		if(data==null) {
			return;
		}
		boolean dataFound=false;
                //create copy root
		DoublyNode<T> currentNode=this.root;
		while(!dataFound) { 
                    if(currentNode.getData().equals(data)) {
                    //create copy prev node
                        DoublyNode<T> previousNode=currentNode.getPrevious();
                    //create copy next node    
                        DoublyNode<T> nextNode=currentNode.getNext();
                        //if prev node != null, set prev.next = next node
                        if(previousNode !=null) {
                            //prev.next = curr.next
                                previousNode.setNext(nextNode);
                        }
                        else {
                        //if curr.prev is null==1s node, root point to nextnode
                                this.root=nextNode;
                        }
                        if(nextNode !=null) {
                                //set next.prev = curr.prev
                                nextNode.setPrevious(previousNode);
                        }
                        dataFound=true;
                    }
                    else {
                            currentNode=currentNode.getNext();
                    }
		}
		size--;
		resetIteratorPointers();
	}
	
        @Override
	public void deleteByIndex( int index) {//Delete node by index
                if(this.root==null) {
                        return;
                }
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Index not available."); 
		}
		// If index is 0 , means needs to remove first node
		if(index == 0) {
                    DoublyNode<T> secondNode = this.root.getNext();
                    if(secondNode!=null) {
                    secondNode.setPrevious(null);
                    }
                    this.root=secondNode;
		}
		// If index is last , means needs to remove last node
		else if(index == this.size -1) {
			DoublyNode<T> lastNode = getLastNode(this.root);
			DoublyNode<T> secondLastNode = lastNode.getPrevious();
			secondLastNode.setNext(null);
		}
		// Remove any index data
		else {
			DoublyNode<T> nodeToBeDelete = getNode(index);
			DoublyNode<T> next = nodeToBeDelete.getNext();
			DoublyNode<T> previous = nodeToBeDelete.getPrevious();
			next.setPrevious(previous);
			previous.setNext(next);
		}
		size--;
		resetIteratorPointers();
	}
	 
        /***********************************/
        @Override
        public boolean contain(T value) throws NullPointerException {// check for the similar node from the linklist
            int i = 1;
            boolean flag = false;
            //Node current will point to head
            DoublyNode<T> curr = root;
            //Checks whether the list is empty
            if(root == null) { 
                return false;
            }
            while(curr != null) {
                //Compare value to be searched with each node in the list
                if(curr.getData().equals(value)) {
                    flag = true;
                    break;
                }
                curr = curr.getNext();
                i++;
            }  
                return flag;
        }
        /******************/
        @Override
	public int size() {//return size of the linklist node
            if (!isEmpty()) {
                return this.size;
            } else {
                return 0;
            } 
	}
        /******************/
        @Override
	public void clear() {//clear the double linklist
            if(root==null){
                System.out.println("The List is empty");
            }else{
                this.root =  this.trailer=null;
                this.iteratorPointer=this.reverseIteratorPointer=null; 
		this.size = 0;
            } 	
	} 
        
        @Override
        public boolean isEmpty() { return (size == 0); }//return true if linklist is empty
	/******************/  
	@Override
	public boolean equals(Object obj) {//checking if this node is equal with the current object node
                //sddress same
		if (this == obj)
			return true;
                //value==null
		if (obj == null)
			return false;
                //same class
		if (!(obj instanceof DoublyLinkedList))
			return false;
                //down cast
		DoublyLinkedList other = (DoublyLinkedList) obj;//other==obj
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
                //size same return0
		if (size != other.size)
			return false; 
		return true;
	}
	@Override
	public String toString() {
            String represent = "";
            DoublyNode<T> nextNode = this.root;
            for (int i = 0; nextNode != null; i++) {
                represent = represent+(i+1)+". " + nextNode.toString();
			nextNode = nextNode.getNext();
			if (nextNode != null) {
				represent = represent + "\n";
			}
            } 
            represent = represent + "";
            return represent;
	} 
}

