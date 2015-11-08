/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.util.Set;

/**
 * 用EnumSet代替位域
 * 
 * @since 1.8
 * @author Mingle
 */
public class EnumSet {
	class Text {
		public static final int STYLE_BOLD = 1 << 0;
		public static final int STYLE_ITALIC = 1 << 1;
		public static final int STYLE_UNDERLINE = 1 << 2;
		public static final int STYLE_STRIKETHROUGH = 1 << 3;
		
		public void applyStyle(int styles) {}
	}
	
	enum Style {
		BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
	}
	
	static class TextEnumSet {
		public void applyStyle(Set<Style> styles) {}
	}
	
	public static void main(String[] args) {
		TextEnumSet text = new TextEnumSet();
		text.applyStyle(java.util.EnumSet.of(Style.BOLD, Style.ITALIC));
	}
}
