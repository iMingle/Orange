/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.database.mapdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Pump;

/**
 * 
 * 
 * @author mingle
 */
public class MapDbTest {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // create database and insert some data
        DB db = DBMaker.memoryDB().transactionDisable().make();
        Set<String> s = db.hashSet("backup");
        s.add("one");
        s.add("two");

        // make full backup
        File backupFile = File.createTempFile("mapdbTest", "mapdb");
        FileOutputStream out = new FileOutputStream(backupFile);

        Pump.backupFull(db, out);
        out.flush();
        out.close();

        // now close database and create new instance with restored content
        db.close();
        DB db2 = Pump.backupFullRestore(
        // configuration used to instantiate empty database
                DBMaker.memoryDB().transactionDisable(),
                // input stream with backup data
                new FileInputStream(backupFile));

        Set<String> s2 = db2.hashSet("backup");
        System.out.println(s2);
    }

}
