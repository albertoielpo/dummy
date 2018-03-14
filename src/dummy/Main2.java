package dummy;

public class Main2 {

	public static void func(String... pars) {
		System.out.println(pars);
	}
	
	public static void main(String[] args) {
        StringBuilder str = new StringBuilder("");
    	String query = "update janus.alarm as m " +
        		"set m.alarm_type = ? , m.updateTimestamp = ?," +                		
        		"m.version = m.version + 1, triggerDisabled = true " +
        		"where lower(m.message) like ? " +
        		"and m.messageKey in (zzzMessageKey) ";
        
        for(int ii=0; ii<3; ii++) {
        	str.append("?,");
        }
        
        str.setLength(str.length() - 1);
        
        query = query.replaceAll("zzzMessageKey", str.toString());

        System.out.println(query);
		
        String subsystemClass = "a.";
		String pippo = (subsystemClass.length() > 1 && subsystemClass.lastIndexOf(".") != -1 ? 
				subsystemClass.substring(subsystemClass.lastIndexOf(".") + 1, subsystemClass.length()) : 
					subsystemClass);
        System.out.println(pippo);
	}

}
