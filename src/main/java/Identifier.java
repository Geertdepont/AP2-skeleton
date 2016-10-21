public class Identifier implements IdentifierInterface{
	private String value;
	
	Identifier(String value){
		this.value=value;
	}
	
	@Override
	public String getValue(){
		return value;
	}
	
	@Override
	public int compareTo(IdentifierInterface other){	
		return value.compareTo(other.getValue());
	}
	
	@Override
	public boolean equals(Object rhs){
		if(rhs==null){
			return false;
		}
		if(this==rhs){
			return true;
		}
		if(getClass()!=rhs.getClass()){
			return false;
		}
		Identifier x=(Identifier)rhs;
		return value.equals(x.value);
	}
	
	@Override
	public int hashCode(){
		return value.hashCode();
	}

}
