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

package org.orange.util.i18n;

import java.awt.Component;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;

/**
 * This combo box lets a user pick a locale. The locales are displayed in the
 * locale of the combo box, and sorted according to the collator of the display
 * locale.
 * 
 * @author mingle
 */
public class LocaleCombo extends JComboBox<Object> {
    private static final long serialVersionUID = -895992472810878939L;

    private Locale[] locales;
    private ListCellRenderer<?> renderer;

    /**
     * Constructs a locale combo that displays an immutable collection of
     * locales.
     * 
     * @param locales
     *            the locales to display in this combo box
     */
    public LocaleCombo(Locale[] locales) {
        this.locales = (Locale[]) locales.clone();
        sort();
        setSelectedItem(getLocale());
    }

    public void setLocale(Locale newValue) {
        super.setLocale(newValue);
        sort();
    }

    private void sort() {
        Object selected = getSelectedItem();
        final Locale loc = getLocale();
        final Collator collator = Collator.getInstance(loc);
        final Comparator<Locale> comp = new Comparator<Locale>() {
            public int compare(Locale a, Locale b) {
                return collator.compare(a.getDisplayName(loc),
                        b.getDisplayName(loc));
            }
        };
        Arrays.sort(locales, comp);
        setModel(new ComboBoxModel<Object>() {
            public Object getElementAt(int i) {
                return locales[i];
            }

            public int getSize() {
                return locales.length;
            }

            public void addListDataListener(ListDataListener l) {
            }

            public void removeListDataListener(ListDataListener l) {
            }

            public Object getSelectedItem() {
                return selected >= 0 ? locales[selected] : null;
            }

            public void setSelectedItem(Object anItem) {
                if (anItem == null)
                    selected = -1;
                else
                    selected = Arrays.binarySearch(locales, (Locale) anItem,
                            comp);
            }

            private int selected;
        });
        setSelectedItem(selected);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ListCellRenderer getRenderer() {
        if (renderer == null) {
            final ListCellRenderer<Object> originalRenderer = super.getRenderer();
            if (originalRenderer == null)
                return null;
            renderer = new ListCellRenderer<Object>() {
                public Component getListCellRendererComponent(JList<?> list,
                        Object value, int index, boolean isSelected,
                        boolean cellHasFocus) {
                    String renderedValue = ((Locale) value)
                            .getDisplayName(getLocale());
                    return originalRenderer.getListCellRendererComponent(list,
                            renderedValue, index, isSelected, cellHasFocus);
                }
            };
        }
        return renderer;
    }

    public void setRenderer(ListCellRenderer<Object> newValue) {
        renderer = null;
        super.setRenderer(newValue);
    }

}
