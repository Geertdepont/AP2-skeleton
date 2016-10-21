/** 
 * @elements : characters of type Char
 * @structure : linear
 * @domain : any row of characters starts with a letter and only contains letters and numbers
 *
 * @constructor - Identifier (string s);
 * PRE - s is an identifier
 * POST - The new Identifier object has been created with the value s
 */

public interface IdentifierInterface  extends Comparable<IdentifierInterface>{
	
	/**
     * @precondition -
     * @postcondition - The value associated with this identifier has been returned as a String.
     */
	String getValue();

}
