/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java.util;

import java.util.*;

/**
 * Pretty-printer for collections
 *
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class PPrint {
	public static String pformat(Collection<?> c) {
		if (c.size() == 0)
			return "[]";
		StringBuilder result = new StringBuilder("[");
		for (Object elem : c) {
			if (c.size() != 1)
				result.append("\n  ");
			result.append(elem);
		}
		if (c.size() != 1)
			result.append("\n");
		result.append("]");
		return result.toString();
	}

	public static void pprint(Collection<?> c) {
		System.out.println(pformat(c));
	}

	public static void pprint(Object[] c) {
		System.out.println(pformat(Arrays.asList(c)));
	}
} // /:~
