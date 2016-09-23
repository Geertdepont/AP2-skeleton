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
    
    List (){
    	list = new Node(null); //empty list
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
        return 0;
    }

 
    
    //still needs work to order in place
	@Override
    public ListInterface<E> insert(E d) {
    	if(list.data==null || list.prior==null){//
    		Node node = new Node(d,list,null); //create new node with the data linked to previous
        	list.next = node;  //link current node to new node
    		list = node; //update list
    		return this;
    	}
		if(list.data.compareTo(d)>0){
			list = list.prior;
			System.out.println("number is smaller");
		}

    	Node node = new Node(d,list,list.next); //create new node with the data linked to previous
    	list.next = node;  //link current node to new node
		list = node; //update list
    	return this;
    }

    @Override
    public E retrieve() {
        return list.data;
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
        if(list.data==null){
        	return false;
        }
    	while(list.prior.prior!=null){
        	list = list.prior;
        }
    	return list!=null;
    }

    @Override
    public boolean goToLast() {
    	while(list.next!=null){
        	list = list.next;
        }
    	return list.data!=null;
    }

    @Override
    public boolean goToNext() {
        if(list.next!=null){
        	list = list.next;
        	return true;
        }
    	return false;
    }

    @Override
    public boolean goToPrevious() {
        if(list.prior.data!=null){
        	list = list.prior;
        	return true;
        }
    	return false;
    }

    @Override
    public ListInterface<E> clone() {
        return null;
    }
    
}
