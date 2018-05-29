package test;

import test.bean.CurBean;
import test.bean.IResponseBean;
import test.bean.NestedCurBean;
import utils.HttpRequestUtils;
import utils.Utils;

public class HttpMainTest {

	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder("");
		
		/* get data */
		/* with appender */
		HttpRequestUtils.get("http://192.168.55.210/awn/queue/get").receive(sb);
		/* with string */
		String body = HttpRequestUtils.get("http://192.168.55.210/awn/queue/get").body();
		
		IResponseBean rb = (CurBean) Utils.deserialize(sb.toString(), CurBean.class, true);
		
		System.out.println(body);
		System.out.println(rb.getDataAsList());
		System.out.println(rb.getDataAsList().get(0));		
		System.out.println(((NestedCurBean) rb.getDataAsList().get(0)).name);
		
		/* test */
		int response = HttpRequestUtils.post("http://192.168.55.210/awn/queue/add").send("name=7999999").code();
		System.out.println(response);
		
	
	}

}
