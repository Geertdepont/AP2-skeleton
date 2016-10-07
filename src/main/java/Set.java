public class Set implements SetInterface{
	List setList;
	
	Set(){
		setList = new List();
	}
	
	Set(ListInterface list){
		setList = (List) list.copy();
	}
	
	@Override
	public void add(Comparable e) {
		if(!setList.find(e)){
			setList.insert(e);
		}
	}

	@Override
	public void remove(Comparable e) {
		if(setList.find(e)){
			setList.remove();
		}
	}

	@Override
	public boolean get(Comparable e) { //could also change to return a element of type E
		return setList.find(e);
	}

	@Override
	public SetInterface union(SetInterface set) {
		Set result = new Set(setList.copy());
		Set temp = (Set) set.copy();
		while(!temp.setList.isEmpty()){
			result.add(temp.getRandom());
			temp.remove(temp.getRandom());
		}
		return result;
	}
	
	

	@Override
	public SetInterface intersection(SetInterface set) {
		Set result = new Set();
		Set temp = (Set) set.copy();
		while(!temp.setList.isEmpty()){
			if(setList.find(temp.getRandom())){
				result.add(getRandom());
			}
			temp.remove(temp.getRandom());
		}
		return result;
	}

	@Override
	public SetInterface complement(SetInterface set) {// this(A) complement set(B)
		Set result = (Set) copy();
		Set temp = (Set) intersection(set);
		while(!temp.setList.isEmpty()){
			result.remove(temp.getRandom());
			temp.remove(temp.getRandom());
		}
		return result;
	}

	@Override
	public SetInterface symmetricDifference(SetInterface set) {
		Set result = (Set) union(set);
		Set temp = (Set) intersection(set);
		while(!temp.setList.isEmpty()){
			result.remove(temp.getRandom());
			temp.remove(temp.getRandom());
		}
		return result;
	}

	@Override
	public Comparable getRandom() {
		return setList.retrieve();
	}

	@Override
	public SetInterface copy() {
		return new Set(setList.copy());
	}

}
