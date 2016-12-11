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

package org.mingle.orange.designpattern.behavioral.mediator;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

/**
 * 字体对话框
 * 具体中介者
 * 
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
