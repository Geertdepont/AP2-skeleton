
//public class Identifier implements IdentifierInterface, Comparable<Identifier>{
public class Identifier implements IdentifierInterface{
	String value; //consider nameIdentifier vs value
	
	Identifier(String value){
		this.value=value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
//	public int compareTo(Identifier other) {	
//		return value.compareTo(other.value);
	public int compareTo(IdentifierInterface other) {	
		return value.compareTo(other.getValue());
	}
	
}
