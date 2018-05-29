package test.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public abstract class ResponseBean<E> implements IResponseBean {
	
	@XmlElement(name="status")
	public String status;
	
	@XmlElement(name="data")
	public String data;
	
	protected List<E> dataAsList;

	public abstract List<E> getDataAsList();
	
}
