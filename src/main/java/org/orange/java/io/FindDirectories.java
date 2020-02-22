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

package org.orange.java.io;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author mingle
 */
public class FindDirectories {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // if no arguments provided, start at the parent directory
        if (args.length == 0) args = new String[] {".."};
        
        try {
            File pathName = new File(args[0]);
            String[] fileNames = pathName.list();
            
            // enumerate all files in the directory
            for (int i = 0; i < fileNames.length; i++) {
                File f = new File(pathName.getPath(), fileNames[i]);
                
                // if the file is again a directory, call the main method recursively
                if (f.isDirectory()) {
                    System.out.println(f.getCanonicalPath());
                    main(new String[] {f.getPath()});
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
