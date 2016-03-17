/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.designpattern.behavioral.mediator;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

/**
 * 字体对话框
 * 具体中介者
 * 
 * @since 1.0
 * @author Mingle
 */
public class FontDialogDirector extends DialogDirector {
    private Button ok;
    private Button cancel;
    private ListBox fontList;
    private EntryField fontName;

    @Override public void changeWidget(Widget widget) {
        if (widget == fontList)
            fontName.setText(fontList.selection());
        else if (widget == ok) {
            fontName.setText(fontList.selection());
            closeDialog();
        } else if (widget == cancel)
            closeDialog();
    }

    @Override protected void createWidgets() {
        ok = new Button(this);
        cancel = new Button(this);
        fontList = new ListBox(this);
        fontName = new EntryField(this);
        
        List<String> items = new ArrayList<>();
        for (Font font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
            items.add(font.getName());
        }
        fontList.setList(items);
    }

}
