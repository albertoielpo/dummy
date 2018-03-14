package dummy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public class LabdaTest {

	@SuppressWarnings({ "rawtypes", "unchecked", "null" })
	public static void main(String[] args) {

		/* ************************************************************************************* */
		/* use of LocalDateTime */
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println(currentTime);
		System.out.println("day of week: " + currentTime.getDayOfWeek());

		/* ************************************************************************************* */
		
		// Anonymous Thread , Runnable
		Thread t1 =  
				new Thread(new Runnable() {
					@Override
					public void run(){
						System.out.println("t1");
					}
				});

		// Anonymous Thread, java 8 syntax
		Thread t2 = new Thread(() -> {
			System.out.println("start t2");
			System.out.println("end t2");
		});
		
		// Anonymous Runnable
		Runnable r1 = new Runnable() {
			public void run() {
				System.out.println("r1");
			}
		};

		// Lambda Runnable
		Runnable r2 = () -> System.out.println("Hello world two!");

		Runnable r3 = () -> {
			System.out.println("start");
			//do something
			System.out.println("end");
		};
		
		//test
		r1.run();	//run sync
		t1.start();	//run async
		
		//run sync with lambda
		r2.run();	
		r3.run();
		
		t2.start();

		/* ************************************************************************************* */
		
		// on-demand implementation using lambda expression
		MyPippo myPippo = (String a) -> {
			return a.length() + " " + a;
		};
		
		System.out.println(myPippo.getPluto("zio"));
		
		//it is equals to write this...
		MyPippoImpl myPippoImpl = new MyPippoImpl();
		System.out.println(myPippoImpl.getPluto("zio"));

		//another on-demand implementation of the same method
		MyPippo mp = (String b) -> {
			return Math.random() + " " + b;
		};
		
		System.out.println(mp.getPluto("zzz"));
		
		
		//usage of lambda in forEach loop of a list obj
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		StringBuilder strb = new StringBuilder("");
		numbers.forEach(v -> strb.append(v + ","));

		System.out.println("values: " + strb.substring(0, strb.length()-1));

		Map<String, String> m = new HashMap<String, String>();
		m.put("k1", "v1");
		m.put("k2", "v2");

		//usage of lambda in forEach loop of Map object
		m.forEach((k, v) -> System.out.println("key: " + k + " value: " + v));

		MyBean mb1 = new MyBean("a", Arrays.asList("a1", "a2", "a3", "a4"));
		MyBean mb2 = new MyBean("b", Arrays.asList("b1", "b2", "b3", "b4"));
		List<MyBean> lmb = new ArrayList<MyBean>();
		lmb.add(mb1);
		lmb.add(mb2);

		// print
		lmb.forEach(myBean -> System.out.println(myBean.getV1() + " " + myBean.getV2().toString()));
		
		////////////////
		MyObj obj = new MyObj(null) {{	this.setMyVar("pd"); }};
		System.out.println(obj.getMyVar());	//pd

        /* fun with stream */
		/* ATTENZIONE AD ARRAYS.ASLIST - Returns a fixed-size list backed by the specified array */
		List<MyObj> listMyObj = Arrays.asList(new MyObj("ab",1), new MyObj("bc"), new MyObj("cd"));		
		MyObj xyz = new MyObj("de",1);

		try{
			listMyObj.add(xyz);			//throws exception		
			listMyObj.remove(xyz);		//throws exception
			listMyObj.clear(); 			//throws exception
			
		} catch(Exception e) {
			listMyObj = new ArrayList<MyObj>(Arrays.asList(new MyObj("ab",1), new MyObj("bc"), new MyObj("cd")));
			listMyObj.add(xyz);		//now works - dynamic list
			listMyObj.remove(xyz);
			listMyObj.clear(); 
			listMyObj.addAll(Arrays.asList(new MyObj("ab",1), new MyObj("bc",2), new MyObj("cd")));
		} 
		
		String name = listMyObj.stream().filter(cur -> "bc".equals(cur.getMyVar())).map(MyObj::getMyVar).findFirst().orElse("");		
		System.out.println(name);

		List<String> resMyObj = listMyObj.stream().map(MyObj::getMyVar).collect(Collectors.toList());
		System.out.println(resMyObj);
		
		List<MyObj> res2 = listMyObj.stream().collect(Collectors.toList());
		System.out.println(res2);
			
		res2.forEach(System.out::println);	//uso il metodo println di system.out
		//è uguale a dire questo
		res2.forEach(x -> System.out.println(x));
		
		/* ****************************************************************************** */
		/* 
		 * Min and Max of a collection using a Comparator 
		 */
		final Comparator<MyObj> comp = (p1, p2) -> Integer.compare( p1.getMyInt(), p2.getMyInt());
		Integer minVal = listMyObj.stream().filter(cur -> cur.getMyInt() != null).min(comp).map(MyObj::getMyInt).get();
		Integer maxVal = listMyObj.stream().filter(cur -> cur.getMyInt() != null).max(comp).map(MyObj::getMyInt).get();
		System.out.println("minVal: " + minVal + " maxVal: " + maxVal); 
		
		/* ****************************************************************************** */
		
		/* remove null from List - Objects::nonNull */
		List<String> abc = new ArrayList(Arrays.asList("ciao","come", null, "stai"));
		List<String> bcd = abc.stream().filter(Objects::nonNull).collect(Collectors.toList());
		System.out.println(abc +">>>"+bcd);
		
		/* safe use of forEach and stream */
		List<String> bbc = null;
		try {
			bbc.forEach(a -> System.out.println(a));
		}catch(Exception e) {
			/* mona! */
			System.out.println("forEach of null -> exception");
		}
		
		try {
			CollectionUtils.emptyIfNull(bbc).forEach(a -> System.out.println(a));
			System.out.println("forEach of CollectionUtils.emptyIfNull do nothing");
			String res = CollectionUtils.emptyIfNull(bbc).stream().findFirst().orElse("else-value");			
			System.out.println("res: " + res);
		} catch (Exception e) {
			/* never here */
			System.out.println("bravo! cosi si fa");
		}

		/* Use case without apache collection Utils */
		List<String> b = null;
		/* with custom util */
		List<String> a = Utils.emptyIfNull(b).stream().collect(Collectors.toList());
		/* aggressive */
		List<String> c = (List<String>) (b == null ? new ArrayList() : b).stream().collect(Collectors.toList());
		a.add("a");
		c.add("c");
		System.out.println("a" + a +" >>> c "+ c);
		
		/* ****************************************************************************** */
		
		
	}

}
