/**
 * Copyright (c) 2014 MNCC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * @author http://www.mncc.fr
 */
package fr.mncc.sandbox.client.layouts.sidemenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import fr.mncc.sandbox.client.assets.SandboxResourceBundle;

public class SideMenuLayout extends Composite {

    @UiTemplate("SideMenuLayout.ui.xml")
    interface MyUiBinder extends UiBinder<Widget, SideMenuLayout> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField HTMLPanel layout;
    @UiField DivElement menu;
    @UiField AnchorElement menuLink;

    public SideMenuLayout() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        // Inject layout stylesheet
        SandboxResourceBundle.INSTANCE.sideMenuLayoutCss().ensureInjected();

        // Bind click event to menu button
        DOM.setEventListener(menuLink, new EventListener() {

            @Override public void onBrowserEvent(Event event) {
                if (event.getTypeInt() == Event.ONCLICK) {
                    toggleClass(layout.getElement(), SandboxResourceBundle.INSTANCE.sideMenuLayoutCss().active());
                    toggleClass(menu, SandboxResourceBundle.INSTANCE.sideMenuLayoutCss().active());
                    toggleClass(menuLink, SandboxResourceBundle.INSTANCE.sideMenuLayoutCss().active());
                    event.preventDefault();
                }
            }
        });
        DOM.sinkEvents(menuLink, Event.ONCLICK);
    }

    @Override
    protected void onUnload() {

        // Remove click event handler in order to avoid memory leaks
        DOM.setEventListener(menuLink, null);
        super.onUnload();
    }

    private void toggleClass(Element element, String className) {
        String elementClasses = element.getClassName();
        if (elementClasses == null) {
            element.setClassName(className);
        }
        else if (elementClasses.contains(className)) {
            element.setClassName(elementClasses.replace(className, ""));
        }
        else {
            element.setClassName(elementClasses + " " + className);
        }
    }
}
