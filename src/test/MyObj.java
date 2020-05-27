package test;

import java.io.Serializable;

public class MyObj implements Serializable {

	private static final long serialVersionUID = -7464253767424762344L;
	private String myVar;
	private Integer myInt;

	public MyObj() {
		
	}
	
	public MyObj(String par) {
		this.myVar = par;
	}
	
	public MyObj(String par, Integer par2){
		this.myVar = par; this.myInt = par2;		
	}
	
	public String getMyVar() {
		return myVar;
	}

	public void setMyVar(String myVar) {
		this.myVar = myVar;
	}

	public Integer getMyInt() {
		return myInt;
	}

	public void setMyInt(Integer myInt) {
		this.myInt = myInt;
	}
	
	@Override
	public String toString(){
		return myVar + " " + myInt;
	}
	
}
