import java.lang.reflect.Array;

public class List<E extends Comparable> implements ListInterface<E>{
	
    private class Node {

        E data;
        Node prior,
                next;

//        public Node(){
//        	this(null);
//        }
        
        public Node(E d) {
            this(d, null, null);
        }

        public Node(E data, Node prior, Node next) {
            this.data = data == null ? null : data;
            this.prior = prior;
            this.next = next;
        }
    }
    
    Node head;//we'll rename it to head
    Node tail;//not implemented yet
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
    	head=null;
    	current = head;
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
		if(head==null){
			//list is empty
			current=head = new Node(d, null, null);
			tail= head;
		}else if(head.data.compareTo(d)>=0){//d is smaller or equal to list.data
			current=head = new Node(d, null, head);
			head.next.prior = head;
		}else{//list.data.compareTo(d)<0
			goToFirst();
			while(current.next!=null && current.next.data.compareTo(d)<0 ){
				goToNext();
			}
			if(current.next!=null){//current somewhere in the middle of list
				current.next=current.next.prior=new Node(d,current,current.next);
			}else{//current at end of list
				current.next=new Node(d,current,null);
			}
			goToNext();//advance current to point to the newly added node
		}
		numberOfElements+=1;
		return this;
    }

    @Override
    public E retrieve() {
    	return head!=null ? current.data : null;
    }
    
    @Override
    public ListInterface<E> remove() {
    	if(head!=null){
    		if(current.next==null){//current at end of list
        		if(current.prior!=null){//list has >1 element
        			current.prior.next = null;
                	current = current.prior;
        		}else{//list has 1 element
        			current=head =null;
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
    public boolean find(E d) {//not implemented yet
        if(isEmpty()){
        	return false;
        }
        goToFirst();

        while(current.next!=null && current.data.compareTo(d)<0){//d is bigger to list.data){
        	current = current.next;
        }
        if(current.data.compareTo(d)>0){
        	return false;
        }
        else{
        	return true;
        }
    }


    
    @Override
    public boolean goToFirst() {
    	if(head!=null){
    		current = head;
    	}
  		return head!=null;
    }

    @Override
    public boolean goToLast() {
    	while(head !=null && current.next!=null){
        	current = current.next;
        }
    	return head!=null;
    }

    @Override
    public boolean goToNext() {
    	if(head !=null && current.next!=null){
        	current = current.next;
        	return true;
        }
    	return false;
    }

    @Override
    public boolean goToPrevious() {
        if(current.prior!=null){
        	current = current.prior;
        	return true;
        }
    	return false;
    }

	@Override
	public ListInterface<E> copy() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
