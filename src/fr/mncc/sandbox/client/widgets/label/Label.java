package fr.mncc.sandbox.client.widgets.label;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class Label extends Composite {

    private String id_;
    private SpanElement element_;

    @UiConstructor  // Ensure this widget can be used inside a UiBinder file
    public Label(String id, String text) {
        id_ = id;
        initWidget(new HTMLPanel(SafeHtmlUtils.fromTrustedString("<span id=\"" + id + "\">" + text + "</span>")));
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        element_ = DOM.getElementById(id_).cast();
    }

    @Override
    protected void onUnload() {
        super.onUnload();
    }

    public void setText(String text) {
        if (element_ != null) {
            element_.setInnerText(text == null ? "" : text);
        }
    }
}
