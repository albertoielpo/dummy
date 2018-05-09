package bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.Utils;

public class CurBean extends ResponseBean<NestedCurBean> {
	
	@Override
	public List<NestedCurBean> getDataAsList() {
		if(this.dataAsList != null)
			return this.dataAsList;
		
		if(this.data != null && !this.data.equals("")) {
			this.dataAsList = new ArrayList<NestedCurBean>(
					Arrays.asList((NestedCurBean[]) Utils.deserialize(this.data, NestedCurBean[].class, false)));
			return this.dataAsList;
		}
		
		return null;
	}

}
