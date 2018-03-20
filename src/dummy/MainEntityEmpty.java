package dummy;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class MainEntityEmpty {
		
	public static boolean ObjectUtilsIsEmpty(Object obj) {
		if (obj == null) {
			return true;
		}

		if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		if (obj instanceof CharSequence) {
			return ((CharSequence) obj).length() == 0;
		}
		if (obj instanceof Collection) {
			return ((Collection) obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map) obj).isEmpty();
		}

		// else
		return false;
	}
	
	private static boolean isEmptyEntityNoFallback(Object entity) {
		boolean entityNull = true;
		if(!ObjectUtilsIsEmpty(entity)) {
			Class c = entity.getClass();		
			Field[] fields = c.getDeclaredFields();

			for (int ii=0; ii<fields.length; ii++) {
				try {
					if(!ObjectUtilsIsEmpty(fields[ii].get(entity))){
						entityNull = false;
						break;
					} 
				} catch (Exception e) {					
					/* if an exception is thrown the entity field is private */
					continue;
				}
			}
			
			Method[] methods = c.getDeclaredMethods();
			for (int ii = 0; ii < methods.length; ii++) {
				try {
					if(!ObjectUtilsIsEmpty(methods[ii].invoke(entity, null))){
						entityNull = false;
						break;
					}					
				} catch (Exception e) {					
					continue;
				} 
			}

		} 
			
		return entityNull;
		
	}
	
	private static boolean isEmptyEntity(Object entity) {
		boolean entityNull = true;
		if(!ObjectUtilsIsEmpty(entity)) {
			Class c = entity.getClass();		
			Field[] fields = c.getDeclaredFields();

			int privateField=0;
			for (int ii=0; ii<fields.length; ii++) {
				try {
					if(!ObjectUtilsIsEmpty(fields[ii].get(entity))){
						entityNull = false;
						break;
					} 
				} catch (Exception e) {					
					/* if an exception is thrown the entity field is private */
					privateField++;
					continue;
				}
			}
			
			/* Safe exit - if all fields of the entity are private we assume that the entity is not empty */
			if(privateField == fields.length)
				entityNull = false;

		} 
			
		return entityNull;
		
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		MyObj2 obj = new MyObj2();
		Entity entity = new Entity();
		Entity entity2 = new Entity();
		
		entity.listMyObj = new ArrayList<MyObj2>();
		entity.listMyObj.add(obj);
		
		entity2.setListPrivate(entity.listMyObj);
				
		System.out.println(isEmptyEntity(null));	//true
		System.out.println(isEmptyEntity(entity));	//false

		/* 
		 * isEmptyEntity = true
		 * sbagliato, viene ispezionata solo la lista pubblica che in questo caso è vuota 
		 */
		System.out.println(isEmptyEntity(entity2));	
		
		/*
		 * isEmptyEntity = false
		 * sbagliato, la lista privata è popolata e non viene ispezionata con il suo metodo pubblico
		 */
		System.out.println(isEmptyEntity(obj));
		System.out.println("================");
		
		System.out.println(isEmptyEntityNoFallback(null));		//true
		System.out.println(isEmptyEntityNoFallback(entity));	//false
		System.out.println(isEmptyEntityNoFallback(entity2));	//false 
		System.out.println(isEmptyEntityNoFallback(obj));		//true
	}

}
