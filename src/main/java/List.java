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
    
    Node list;//we'll rename it to head
    Node tail;//not implemented yet
    Node current;
    int numberOfElements;
    
    List (){
    	init();
    }
   
    @Override
    public boolean isEmpty() {
        return list==null;
    }

    @Override
    public ListInterface<E> init() {
    	list=null;
    	current = list;
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
		if(list==null){
			//list is empty
			current=list = new Node(d, null, null);
		}else if(list.data.compareTo(d)>=0){//d is smaller or equal to list.data
			current=list = new Node(d, null, list);
			list.next.prior = list;
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
    	return list!=null ? current.data : null;
    }
    
    @Override
    public ListInterface<E> remove() {
    	if(list!=null){
    		if(current.next==null){//current at end of list
        		if(current.prior!=null){//list has >1 element
        			current.prior.next = null;
                	current = current.prior;
        		}else{//list has 1 element
        			current=list =null;
        		}
        	}else if(current.prior==null){//current is at start of list
        		current=list=list.next;
        		list.prior=null;
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
        return false;
    }


    
    @Override
    public boolean goToFirst() {
    	if(list!=null){
    		current = list;
    	}
  		return list!=null;
    }

    @Override
    public boolean goToLast() {
    	while(list !=null && current.next!=null){
        	current = current.next;
        }
    	return list!=null;
    }

    @Override
    public boolean goToNext() {
    	if(list !=null && current.next!=null){
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
