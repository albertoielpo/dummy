package test;

import java.lang.reflect.Method;

public class SabaudoDaoImpl extends SabaudoDaoAbs implements SabaudoDao {

	public long a;
	
	@Override
	@SabaudoAnnotation(stato = "statoSabaudo")
	public boolean isSabaudo() {
		try {
			/*
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String className = stackTraceElements[1].getClassName();
			String methodName = stackTraceElements[1].getMethodName();
			System.out.println(className + " " + methodName);
			*/
	        Method m = new Object() {}.getClass().getEnclosingMethod();
	        if(m.isAnnotationPresent(SabaudoAnnotation.class)) {
				SabaudoAnnotation annotation = m.getDeclaredAnnotation(SabaudoAnnotation.class);
				System.out.println("annotation.stato: " + annotation.stato());
			}


						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void statoSabaudo() {
		System.out.println("oh yeah statoSabaudo");
	}

}
