package test;

import java.io.Serializable;
import java.math.BigDecimal;

public class SerializableHashCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4550292724248109166L;

	private String a;
	
	private Integer b;
	
	private BigDecimal c;

	public SerializableHashCode() {};
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public Integer getB() {
		return b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

	public BigDecimal getC() {
		return c;
	}

	public void setC(BigDecimal c) {
		this.c = c;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
	    if (o instanceof SerializableHashCode) {
	    	SerializableHashCode e = (SerializableHashCode) o;
	    	if(this.a == null) {
	    		if(e.a != null) 
	    			return false;
	    	}
	    	if(this.a != null && e.a != null && !this.a.toString().equals(e.a.toString()))
	    		return false;
	    	
	    	if(this.b == null) {
	    		if(e.b != null) 
	    			return false;
	    	}
	    	if(this.b != null && e.b != null && !this.b.equals(e.b))
	    		return false;
	    	
	    	if(this.c == null) {
	    		if(e.c != null) 
	    			return false;
	    	}
	    	if(this.c != null && e.c != null && !this.c.toString().equals(e.c.toString()))
	    		return false;
	    	
	    	return true;
	    }
	    return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	    int hash = 7;
	    hash = 31 * hash + (a == null ? 0 : a.hashCode());
	    hash = 31 * hash + (b == null ? 0 : b.hashCode());
	    hash = 31 * hash + (c == null ? 0 : c.hashCode());
	    return hash;
	}
	
}
