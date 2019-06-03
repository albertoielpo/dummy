package utils.bean;

import java.io.Serializable;

/**
 * @author Alberto Ielpo
 */
public class Tuple<K, V> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7730881314323511391L;

	/**
     * 
     */
    private K first;
    
    /**
     * 
     */
    private V second;

    /**
     * 
     * @param first
     * @param second
     */
    public Tuple(final K first, final V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return the first
     */
    public K getFirst() {
        return first;
    }
    
    /**
     * @return the first
     */
    public void setFirst(K obj) {
        this.first = obj;
    }

    /**
     * @return the second
     */
    public V getSecond() {
        return second;
    }
    
    public void setSecond(V obj) {
        this.second = obj;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (obj == null)
            return false;
        
        if (!(obj instanceof Tuple))
            return false;
        
        Tuple other = (Tuple) obj;
        
        if (first == null) {
            if (other.first != null)
                return false;
        }
        else if (!first.equals(other.first))
            return false;
        
        if (second == null) {
            if (other.second != null)
                return false;
        }
        else if (!second.equals(other.second))
            return false;
        
        return true;
    }
}
