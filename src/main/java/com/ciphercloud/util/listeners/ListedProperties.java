/**
 * 
 */
package com.ciphercloud.util.listeners;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * @author bhupesh.b
 *
 */

public class ListedProperties extends Properties{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListedProperties() {
    super ();

    names = new Vector<Object>();
}

public Enumeration<Object> propertyNames() {
    return names.elements();
}

public Object put(Object key, Object value) {
    if (names.contains(key)) {
        names.remove(key);
    }

    names.add(key);

    return super .put(key, value);
}

public Object remove(Object key) {
    names.remove(key);

    return super .remove(key);
}

private Vector<Object> names;
}
