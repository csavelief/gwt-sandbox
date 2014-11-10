package fr.mncc.sandbox.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface sandboxServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
