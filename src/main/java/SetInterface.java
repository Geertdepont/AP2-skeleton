/** 
	 * @element elements of type E
	 * @structure no structure
	 * @domain all elements of type E
	 **/
public interface SetInterface <E>{
	 /**
	  * Set <E>()
	  * PRE -
	  * POST An empty set has been created
	  *
	  */
	
	/**
     * @pre -
     * @post The token 'token' has been added at the end of the TokenList, 
     *       preserving the previous order.
     */
    void add(E e);
    
    /**
     * @pre -
     * @post The token 'token' has been added at the end of the TokenList, 
     *       preserving the previous order.
     */
    void remove(E e);
    
    /**
     * @pre -
     * @post The token 'token' has been added at the end of the TokenList, 
     *       preserving the previous order.
     */
    E union(E e);

}
