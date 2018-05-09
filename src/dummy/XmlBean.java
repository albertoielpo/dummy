package dummy;

import javax.xml.bind.annotation.XmlElement;

public class XmlBean {
	
	public XmlBean() {};
	public XmlBean(String a, Integer b) {
		val1 = a;
		val2 = b;
	}

	@XmlElement(name ="val1")
	public String val1;
	
	@XmlElement(name ="val2")
	public Integer val2;
}
