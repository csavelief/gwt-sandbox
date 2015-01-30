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
