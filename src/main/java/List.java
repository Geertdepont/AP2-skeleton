//import java.lang.reflect.Array; //do we need this?

public class List<E extends Comparable<E>> implements ListInterface<E>{
	
    private class Node {

        E data;
        Node prior,
                next;

        public Node(E d) {
            this(d, null, null);
        }

        public Node(E data, Node prior, Node next) {
            this.data = data == null ? null : data;
            this.prior = prior;
            this.next = next;
        }
    }
    
    Node head;
    Node tail;
    Node current;
    int numberOfElements;
    
    List (){
    	init();
    }
   
    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public ListInterface<E> init() {
    	tail=current=head=null;
    	numberOfElements=0;
    	return this;
    }

    @Override
    public int size() {
    	return numberOfElements;
    }

	@Override
    public ListInterface<E> insert(E d) {
		goToFirst();
		if(isEmpty()){//list is empty
			current=head = new Node(d);
			tail= head;
		}else if(head.data.compareTo(d)>=0){//d is smaller or equal to head.data
			current=head = new Node(d, null, head);
			head.next.prior = head;
		}else{//d is bigger than head.data
			goToFirst();
			while(current.next!=null && current.next.data.compareTo(d)<0 ){//d is bigger than current.next.data
				goToNext();
			}
			if(current.next!=null){//current somewhere in the middle of list
				current.next=current.next.prior=new Node(d,current,current.next);
			}else{//current at end of list
				tail=current.next=new Node(d,current,null);
			}
			goToNext();//advance current to point to the newly added node
		}
		numberOfElements+=1;
		return this;
    }
//    @Override
    public ListInterface<E> insert2(E d) {
		if(isEmpty()){
			tail=current=head=new Node(d);
		}else{
			find(d);
			if(current.prior==null && d.compareTo(current.data)<=0){//d<=head; the second check is to determine on a list with only 1 element where to put the new
				current=head=new Node(d,null,head);
				head.next.prior=head;
			}else{
				if(current.data.compareTo(d)==0){//insert before current if current.data=d
					goToPrevious();
				}
				if(current.next!=null){//in between
					current=current.next=current.next.prior=new Node(d,current,current.next);
				}else{//end of list
					tail=current=current.next=new Node(d,current,null);
				}
			}
		}
		numberOfElements+=1;
		return this;
    }

    @Override
    public E retrieve() {
    	return !isEmpty() ? current.data : null;
    }
    
    @Override
    public ListInterface<E> remove() {
    	if(!isEmpty()){
    		if(current.next==null){//current at end of list
        		if(current.prior!=null){//list has >1 element
        			current.prior.next = null;
                	tail=current = current.prior;
        		}else{//list has 1 element
        			tail=current=head =null;
        		}
        	}else if(current.prior==null){//current is at start of list
        		current=head=head.next;
        		head.prior=null;
        	}else{//current is somewhere in the middle of list
        		current.prior.next = current.next;
        		current.next.prior = current.prior;
        		current = current.next;
        	}
        	numberOfElements-=1;
    	}
    	return this;
    }


    @Override
    public boolean find(E d) {
        if(!goToFirst()){
        	return false;
        }
        while(current.next!=null && current.data.compareTo(d)<0 && current.next.data.compareTo(d)<=0){//d is bigger than current.data
        	current=current.next;
        }
        return current.data.compareTo(d)==0;
    }


    
    @Override
    public boolean goToFirst() {
    	if(!isEmpty()){
    		current = head;
    	}
  		return !isEmpty();
    }

    @Override
    public boolean goToLast() {
    	if(!isEmpty()){
    		current=tail;
    	}
    	return !isEmpty();
    }

    @Override
    public boolean goToNext() {
    	if(!isEmpty() && current.next!=null){
        	current = current.next;
        	return true;
        }
    	return false;
    }

    @Override
    public boolean goToPrevious() {
        if(!isEmpty() && current.prior!=null){
        	current = current.prior;
        	return true;
        }
    	return false;
    }

	@Override
	public ListInterface<E> copy() {
		List<E> result=new List<E>();
		if(goToFirst()){
			result.insert(current.data);
			while(goToNext()){
				result.insert(current.data);
			}
		}
		return result;
	}
    
}
