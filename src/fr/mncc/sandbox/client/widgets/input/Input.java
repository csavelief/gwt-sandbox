/**
 * Copyright (c) 2011 MNCC
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
package fr.mncc.sandbox.client.widgets.input;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class Input extends Composite {

    public static class ChangeEvent extends GwtEvent<ChangeEventHandler> {

        public static final Type<ChangeEventHandler> TYPE = new Type<ChangeEventHandler>();
        private String text_ = "";

        public ChangeEvent(String text) {
            text_ = text == null ? "" : text;
        }

        @Override
        public Type<ChangeEventHandler> getAssociatedType() {
            return TYPE;
        }

        @Override
        protected void dispatch(ChangeEventHandler handler) {
            if (handler != null) {
                handler.onChange(text_);
            }
        }
    }

    public interface ChangeEventHandler extends EventHandler {

        void onChange(String text);
    }

    public interface MyHtmlTemplate extends SafeHtmlTemplates {

        @Template("<input id=\"{0}\" type=\"{1}\" class=\"{2}\"/>")
        SafeHtml input(String id, String type, String style);
    }

    private static MyHtmlTemplate htmlTemplate = GWT.create(MyHtmlTemplate.class);
    private String id_;
    private InputElement element_;
    private SimpleEventBus eventBus_;

    public Input(SimpleEventBus eventBus, String id) {
        this(eventBus, id, "text", "");
    }

    public Input(SimpleEventBus eventBus, String id, String type, String style) {
        id_ = id;
        eventBus_ = eventBus;
        initWidget(new HTMLPanel(htmlTemplate.input(id, type, style)));
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        element_ = DOM.getElementById(id_).cast();
        DOM.setEventListener(element_, new EventListener() {

            @Override
            public void onBrowserEvent(Event event) {
                if (eventBus_ != null && element_ != null) {
                    eventBus_.fireEvent(new ChangeEvent(element_.getValue()));
                }
            }
        });
        DOM.sinkEvents(element_, Event.ONKEYUP);
    }

    @Override
    protected void onUnload() {
        DOM.setEventListener(element_, null);
        super.onUnload();
    }

    public String getText() {
        if (element_ != null) {
            String text = element_.getInnerText();
            return text == null ? "" : text;
        }
        return "";
    }
}
