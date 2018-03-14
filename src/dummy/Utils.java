package dummy;

import java.util.ArrayList;
import java.util.Collection;

public class Utils {
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> Collection<T> emptyIfNull(final Collection<T> collection) {        
		return collection == null ? new ArrayList() : collection;
    }
}
