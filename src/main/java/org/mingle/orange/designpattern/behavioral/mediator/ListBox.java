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

package org.mingle.orange.designpattern.behavioral.mediator;

import java.awt.event.MouseEvent;
import java.util.List;

/**
 * 列表框
 *
 * @author mingle
 */
public class ListBox extends Widget {
    @SuppressWarnings("unused") private List<String> items;

    public ListBox(DialogDirector director) {
        super(director);
    }

    public String selection() {
        return "Selected";
    }

    public void setList(List<String> items) {
        this.items = items;
    }

    @Override public void handleMouse(MouseEvent event) {
        super.handleMouse(event);
    }
}
