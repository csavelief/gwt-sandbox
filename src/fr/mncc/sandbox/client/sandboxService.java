package fr.mncc.sandbox.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("sandboxService")
public interface sandboxService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use sandboxService.App.getInstance() to access static instance of sandboxServiceAsync
     */
    public static class App {
        private static sandboxServiceAsync ourInstance = GWT.create(sandboxService.class);

        public static synchronized sandboxServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
