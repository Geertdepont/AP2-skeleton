public class Set<E extends Comparable <E>> implements SetInterface<E>{
	
	private List<E> setList;
	
	public Set(){
		setList = new List<E>();
	}
	
	private Set(ListInterface<E> list){
		setList = (List<E>) list.copy();
	}
	
	@Override
	public void add(E e){
		if(!contains(e)){
			setList.insert(e);
		}
	}

	@Override
	public void remove(E e){
		if(contains(e)){
			setList.remove();
		}
	}
	
	@Override
	public boolean contains(E e){
		return setList.find(e);
	}

	@Override
	public SetInterface<E> union(SetInterface<E>set){
		Set<E> result = (Set<E>) copy();
		Set<E> temp = (Set<E>) set.copy();
		while(!temp.isEmpty()){
			E element=temp.getRandom();
			result.add(element);
			temp.remove(element);
		}
		return result;
	}

	@Override
	public SetInterface<E> intersection(SetInterface<E>set){
		Set<E> result = new Set<E>();
		Set<E> temp = (Set<E>) set.copy();
		while(!temp.isEmpty()){
			E element=temp.getRandom();
			if(contains(element)){
				result.add(element);
			}
			temp.remove(element);
		}
		return result;
	}

	@Override
	public SetInterface<E> complement(SetInterface<E>set){// 'this'(A) complement 'set'(B)
		Set<E> result = (Set<E>) copy();
		Set<E> temp = (Set<E>) intersection(set);
		while(!temp.isEmpty()){
			E element=temp.getRandom();
			result.remove(element);
			temp.remove(element);
		}
		return result;
	}

	@Override
	public SetInterface<E> symmetricDifference(SetInterface<E> set){
		Set<E> result = (Set<E>) union(set);
		Set<E> temp = (Set<E>) intersection(set);
		while(!temp.isEmpty()){
			E element=temp.getRandom();
			result.remove(element);
			temp.remove(element);
		}
		return result;
	}

	@Override
	public E getRandom(){
		return setList.retrieve();
	}

	@Override
	public SetInterface<E> copy(){
		return new Set<E>(setList.copy());
	}

	@Override
	public boolean isEmpty(){
		return setList.isEmpty();
	}

}
