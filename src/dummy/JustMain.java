package dummy;

import bean.CurBean;
import bean.IResponseBean;
import bean.NestedCurBean;
import utils.HttpRequestUtils;
import utils.Utils;

public class JustMain {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("");
		HttpRequestUtils.get("http://192.168.55.210/awn/queue/get").receive(sb);
		
		IResponseBean rb = (CurBean) Utils.deserialize(sb.toString(), CurBean.class, true);
		/* and then */
		System.out.println(rb.getDataAsList());
		System.out.println(rb.getDataAsList().get(0));		
		System.out.println(((NestedCurBean) rb.getDataAsList().get(0)).name);
	}
}
