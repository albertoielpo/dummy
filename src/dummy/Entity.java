package dummy;

import java.util.List;

public class Entity {

	public List<MyObj2> listMyObj;
	private List<MyObj2> listPrivate;
	
	public List<MyObj2> getListPrivate() {
		return listPrivate;
	}
	public void setListPrivate(List<MyObj2> listPrivate) {
		this.listPrivate = listPrivate;
	}
	
}
