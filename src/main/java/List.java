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
//    	list = new Node(null); //empty list
    	
    	list=null;
    	current = list;
    	//OR
//    	init();
    }
   
    @Override
    public boolean isEmpty() {
        return list==null;
    }

    @Override
    public ListInterface<E> init() {
    	list=null;
    	current = list;
    	return this;
    }

    @Override
    public int size() {
//    	goToFirst();
//    	
//        int counter = 0;
//        if(list.data!=null){
//        	counter =1;
//        }
//        
//        while(list.next!=null){
//        	list= list.next;
//        	counter +=1;
//        }
//        
//        goToFirst();
//    	return counter;
    	int size=0;
    	if(list!=null){
    		size+=1;
    	}
    	Node k=list;
    	while(k.next!=null){
    		k=k.next;
    		size+=1;
    	}
    	return size;
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
			list = new Node(d, null, list);
			current = list;
		}
		if(list.data.compareTo(d)<0){
			//number is bigger
			goToFirst();
			while(current.next.data.compareTo(d)<0 || current.next!=null){
				goToNext();
			}
			if(current.next!=null){
				current.next.prior=new Node(d,current,current.next);
				current.next=current.next.prior;
				goToNext();
			}else{//current at end of list
				current.next=new Node(d,current,null);
				goToNext();
			}
		}
		return this;
    }

    @Override
    public E retrieve() {
//    	if(current==null){
//    		return null;
//    	}
//    	return current.data;
    	return list!=null ? list.data : null;
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
    	if(list!=null){
    		current = list;
    	}
  		return list!=null;
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
