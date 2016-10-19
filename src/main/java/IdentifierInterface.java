/** 
 * @element characters of type Char
 * @structure linear
 * @domain any row of characters starts with a letter and only contains letters and numbers
 * @constructor Indentifier (string s);
 * PRE -s is an identifier
 * POST a new object has been created, the value is s
 */


//public interface IdentifierInterface  {
public interface IdentifierInterface  extends Comparable<IdentifierInterface>{
	
	
	/**
     * @pre -
     * @post The value associated with this identifier has been returned as a String.
     */
	String getValue();
	
	/**
     * @pre -
     * @post
     */
	public boolean equals(IdentifierInterface input);
	

}
