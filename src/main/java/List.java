import java.lang.reflect.Array;

public class List<E extends Comparable> implements ListInterface<E>{
	
    private class Node {
//    	class Node {

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
		if(head==null){
			//list is empty
			current=head = new Node(d, null, null);
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
		if(head==null){//list is empty
			tail=current=head=new Node(d, null, null);
		}else{
			find(d);
			if(current.prior==null && d.compareTo(current.data)<=0){//d<=head
				current=head=new Node(d,null,head);
				head.next.prior=head;
			}else if(current.next!=null){//in between
				if(current.data.compareTo(d)==0){//insert before current if current.data=d
					goToPrevious();
				}
				current=current.next=current.next.prior=new Node(d,current,current.next);
			}else{//end of list
				tail=current=current.next=new Node(d,current,null);
			}
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
    public boolean find(E d) {//not implemented yet
//        if(isEmpty()){
//        	return false;
//        }
//        goToFirst();
//
//        while(current.next!=null && current.data.compareTo(d)<0){//d is bigger to list.data){
//        	current = current.next;
//        }
//        if(current.data.compareTo(d)>0){
//        	return false;
//        }
//        else{
//        	return true;
//        }
        
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
    	if(head!=null){
    		current = head;
    	}
  		return head!=null;
    }

    @Override
    public boolean goToLast() {
//    	while(head !=null && current.next!=null){
//        	current = current.next;
//        }
//    	return head!=null
    	if(head!=null){
    		current=tail;
    	}
    	return head!=null;
    }

    @Override
    public boolean goToNext() {
    	if(head!=null && current.next!=null){
        	current = current.next;
        	return true;
        }
    	return false;
    }

    @Override
    public boolean goToPrevious() {
        if(head!=null && current.prior!=null){
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
