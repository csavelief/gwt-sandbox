package fr.mncc.sandbox.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import fr.mncc.sandbox.client.sandboxService;

public class sandboxServiceImpl extends RemoteServiceServlet implements sandboxService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}