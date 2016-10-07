/** 
 * @element elements of type E
 * @structure no structure
 * @domain all unique elements of type E
 * @constructor - Set <E>();
 * PRE -
 * POST An empty set has been created
 *
 */
public interface SetInterface <E extends Comparable<E>>{
	
	/**
     * @pre -
     * @post The element "e" has been added to the Set.
     */
    void add(E e);
    
    /**
     * @pre -
     * @post The element "e" has been removed from the Set.
     */
    void remove(E e);
    
    /**
     * @pre non empty set
     * @post TRUE element "e" is in the Set.
     * 		 FALSE element "e" is not in the Set.
     */
    boolean get(E e);
    
    
    /**
     * @pre -
     * @post Set of 
     */
    E getRandom();
    
    
    
    /**
     * @pre -
     * @post Set of 
     */
    SetInterface<E> copy();
    
    /**
     * @pre -
     * @post The union of this set and "set" has been returned
     */
    SetInterface<E> union(SetInterface<E> set);
    
    /**
     * @pre -
     * @post The intersection of this set and "set" has been returned
     */
    SetInterface<E> intersection(SetInterface<E> set);
    
    /**
     * @pre -
     * @post The complement of this set and "set" has been returned
     */
    SetInterface<E> complement(SetInterface<E> set);
    
    /**
     * @pre -
     * @post The symmetric difference of this set and "set" has been returned
     */
    SetInterface<E> symmetricDifference(SetInterface<E> set);

}
