/*
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
 * limitations under the License.
 */

package org.mingle.orange.util.database.mapdb;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

/**
 * @author mingle
 */
public class MapDbTest {

    public static void main(String[] args) throws IOException {
        // create database and insert some data
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "here");

        db = DBMaker.fileDB("file.db").make();
        map = db.hashMap("map").createOrOpen();
        map.put("something", "here");
        db.close();

        db = DBMaker.fileDB("file.db").fileMmapEnable().make();
        ConcurrentMap<String, Long> map1 = db
                .hashMap("map", Serializer.STRING, Serializer.LONG)
                .createOrOpen();
        map1.put("something", 111L);

        db.close();
    }

}
