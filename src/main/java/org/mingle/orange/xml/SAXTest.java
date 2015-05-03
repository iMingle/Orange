/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This program demonstrates how to use a SAX parser. The program prints all hyperlinks links of an
 * XHTML web page. <br>
 * Usage: java SAXTest url
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class SAXTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = null;
		if (args[0].length() == 0) {
			url = "http://www.w3.org";
			System.out.println("Using " + url);
		} else {
			url = args[0];
		}
		
		DefaultHandler handler = new DefaultHandler() {

			/* (non-Javadoc)
			 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
			 */
			@Override
			public void startElement(String uri, String localName,
					String qName, Attributes attributes) throws SAXException {
				if (localName.equals("a") && attributes != null) {
					for (int i = 0; i < attributes.getLength(); i++) {
						String aname = attributes.getLocalName(i);
						if (aname.equals("href")) System.out.println(attributes.getValue(i));
					}
				}
			}
			
		};
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
			SAXParser saxParser = factory.newSAXParser();
			InputStream in = new URL(url).openStream();
			saxParser.parse(in, handler);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
