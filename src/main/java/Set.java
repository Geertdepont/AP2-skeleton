public class Set<E extends Comparable <E>> implements SetInterface<E>{
	List<E> setList;
	
	public Set(){
		setList = new List<E>();
	}
	
	private Set(ListInterface<E> list){
		setList = (List<E>) list.copy();
	}
	
	@Override
	public void add(E e) {
		if(!setList.find(e)){
			setList.insert(e);
		}
	}

	@Override
	public void remove(E e) {
		if(setList.find(e)){
			setList.remove();
		}
	}

	@Override
	public boolean get(E e) { //could also change to return a element of type E/ If it is boolean, probably should name it 'find' or 'contains'
		return setList.find(e);
	}

	@Override
	public SetInterface<E> union(SetInterface<E>set) {
		Set<E>result = new Set<E>(setList.copy());
		Set<E>temp = (Set<E>) set.copy();
		while(!temp.setList.isEmpty()){
			result.add(temp.getRandom());
			temp.remove(temp.getRandom());
		}
		return result;
	}
	
	

	@Override
	public SetInterface<E> intersection(SetInterface<E>set) {
		Set<E>result = new Set<E>();
		Set<E>temp = (Set<E>) set.copy();
		while(!temp.setList.isEmpty()){
			if(setList.find(temp.getRandom())){
				result.add(getRandom());
			}
			temp.remove(temp.getRandom());
		}
		return result;
	}

	@Override
	public SetInterface<E> complement(SetInterface<E>set) {// 'this'(A) complement 'set'(B)
		Set<E>result = (Set<E>) copy();
		Set<E>temp = (Set<E>) intersection(set);
		while(!temp.setList.isEmpty()){
			result.remove(temp.getRandom());
			temp.remove(temp.getRandom());
		}
		return result;
	}

	@Override
	public SetInterface<E> symmetricDifference(SetInterface<E> set) {
		Set<E> result = (Set<E>) union(set);
		Set<E> temp = (Set<E>) intersection(set);
		while(!temp.setList.isEmpty()){
			result.remove(temp.getRandom());
			temp.remove(temp.getRandom());
		}
		return result;
	}

	@Override
	public E getRandom() {
		return setList.retrieve();
	}

	@Override
	public SetInterface<E> copy() {
		return new Set<E>(setList.copy());
	}

}
