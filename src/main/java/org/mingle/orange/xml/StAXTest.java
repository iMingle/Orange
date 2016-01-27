/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * This program demonstrates how to use a StAX parser. The program prints all hyperlinks links of an
 * XHTML web page. <br>
 * Usage: java StAXTest url
 * 
 * @since 1.8
 * @author Mingle
 */
public class StAXTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String urlString = null;
        if (args[0].length() == 0) {
            urlString = "http://www.w3.org";
            System.out.println("Using " + urlString);
        } else {
            urlString = args[0];
        }
        
        try {
            URL url = new URL(urlString);
            InputStream in = url.openStream();
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            
            while (parser.hasNext()) {
                int event = parser.next();
                if (XMLStreamConstants.START_ELEMENT == event) {
                    if (parser.getLocalName().equals("a")) {
                        String href = parser.getAttributeValue(null, "href");
                        if (href != null)
                            System.out.println(href);
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
