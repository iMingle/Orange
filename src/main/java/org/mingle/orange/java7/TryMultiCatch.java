/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.java7;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * 
 * @since 1.8
 * @author Mingle
 */
public class TryMultiCatch {
	public void test() throws NoSuchMethodException, NoSuchFieldException {
		try {
			Connection conn = DriverManager.getConnection("");
			conn.createStatement();
			File file = new File("");
			file.createNewFile();
		} catch (SQLException | IOException ex) {}
	}
}
