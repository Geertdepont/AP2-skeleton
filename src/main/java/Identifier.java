
public class Identifier implements IdentifierInterface, Comparable<Identifier>{
	String value; //consider nameIdentifier vs value
	
	Identifier(String value){
		this.value=value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
	public int compareTo(Identifier other) {	
		return value.compareTo(other.value);
	}
	
}
