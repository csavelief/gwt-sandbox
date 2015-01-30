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
package fr.mncc.sandbox.client.widgets.button;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Button extends Composite {

    public static abstract class SimpleCallback implements AsyncCallback<Void> {

        @Override
        public void onFailure(Throwable caught) {

        }
    }

    @UiTemplate("Button.ui.xml")
    interface MyUiBinder extends UiBinder<Widget, Button> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    ButtonElement button;

    private SimpleCallback onClickCallback_;

    public Button() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        DOM.setEventListener(button, new EventListener() {

            @Override
            public void onBrowserEvent(Event event) {
                if (onClickCallback_ != null) {
                    onClickCallback_.onSuccess(null);
                }
            }
        });
        DOM.sinkEvents(button, Event.ONCLICK);
    }

    @Override
    protected void onUnload() {
        DOM.setEventListener(button, null);
        super.onUnload();
    }

    public void SetOnClickCallback(SimpleCallback onClickCallback) {
        onClickCallback_ = onClickCallback;
    }
}
