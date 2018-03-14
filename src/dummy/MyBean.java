package dummy;

import java.util.ArrayList;
import java.util.List;

public class MyBean {

	private String v1;
	private List<String> v2;
	
	public MyBean(String v1, List<String> v2) {
		this.v1 = v1;
		this.v2 = new ArrayList<String>(v2);
	}

	public String getV1() {
		return v1;
	}

	public void setV1(String v1) {
		this.v1 = v1;
	}

	public  List<String> getV2() {
		return v2;
	}

	public void setV2( List<String> v2) {
		this.v2 = v2;
	};
	
}
