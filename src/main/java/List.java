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
    
    Node list;
    Node current;
    
    List (){
    	list = new Node(null); //empty list
    	current = list;
    }
   
    @Override
    public boolean isEmpty() {
        return list==null;
    }

    @Override
    public ListInterface<E> init() {
    	return this;
    }

    @Override
    public int size() {
    	goToFirst();
    	
        int counter = 0;
        if(list.data!=null){
        	counter =1;
        }
        
        while(list.next!=null){
        	list= list.next;
        	counter +=1;
        }
        
        goToFirst();
    	return counter;
    }

 
	@Override
    public ListInterface<E> insert(E d) {
		goToFirst();
		
		
		if(list==null){
			//list is empty
			list = new Node(d, null, null);
			current = list;
		}
		if(list.data.compareTo(d)>0){
			//number is smaller
			
		}
		if(list.data.compareTo(d)<0){
			//number is bigger
		}
		return this;
    }

    @Override
    public E retrieve() {
    	if(current==null){
    		return null;
    	}
    	return current.data;
    }

    @Override
    public ListInterface<E> remove() {
    	list.prior.next = list.next;
    	list = list.prior;
    	return this;
    }

    @Override
    public boolean find(E d) {
        return false;
    }


    
    @Override
    public boolean goToFirst() {
    	if(current !=null){
    		current = list;
    	}
  		return current!=null;
    }

    @Override
    public boolean goToLast() {
    	while(current.next!=null){
        	current = current.next;
        }
    	return list!=null;
    }

    @Override
    public boolean goToNext() {
        if(current.next!=null){
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
    public ListInterface<E> clone() {
        return null;
    }
    
    
}
