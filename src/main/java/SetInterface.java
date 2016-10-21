/** 
 * @elements : elements of type E
 * @structure : no structure
 * @domain : all unique elements of type E
 * 
 * @constructor - Set <E>();
 * PRE -
 * POST - An empty set has been created
 *
 */
public interface SetInterface <E extends Comparable<E>>{
	
	/**
     * @precondition -
     * @postcondition - The element e has been added to the Set.
     */
    void add(E e);
    
    /**
     * @precondition -
     * @postcondition - The element e has been removed from the Set.
     */
    void remove(E e);
    
    /**
     * @precondition -
     * @postcondition - TRUE the Set is empty.
     * 		 FALSE the Set is not empty.
     */
    boolean isEmpty();
    
    /**
     * @precondition -
     * @postcondition - TRUE: The set contains element e
     * 		FALSE: The set does not contain element e
     */
    boolean contains(E e);
    
    /**
     * @precondition - The set is not empty.
     * @postcondition - A random element from the set has been returned 
     */
    E getRandom();
    
    /**
     * @precondition -
     * @postcondition - A copy of the set has been returned 
     */
    SetInterface<E> copy();
    
    /**
     * @precondition -
     * @postcondition - The union of this set and "set" has been returned
     */
    SetInterface<E> union(SetInterface<E> set);
    
    /**
     * @precondition -
     * @postcondition - The intersection of this set and "set" has been returned
     */
    SetInterface<E> intersection(SetInterface<E> set);
    
    /**
     * @precondition -
     * @postcondition - The complement of this set and "set" has been returned
     */
    SetInterface<E> complement(SetInterface<E> set);
    
    /**
     * @precondition -
     * @postcondition - The symmetric difference of this set and "set" has been returned
     */
    SetInterface<E> symmetricDifference(SetInterface<E> set);

}
