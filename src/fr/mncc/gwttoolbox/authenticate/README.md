gwt-authenticate
================

Authentication for Single Page Applications.

Dependencies
============

* [gwt-jsonrpc](https://github.com/MNCC/gwt-jsonrpc)

How to build gwt-authenticate ?
===============================

* Download & install [Gradle 1.7](http://www.gradle.org/) or above
* Go to your local gwt-authenticate directory
* Type "gradle" in your command-line
* The generated jar will be written in the [bin/](https://github.com/MNCC/gwt-authenticate/tree/master/bin) directory

How to get started ?
====================

Add the following line to your *.gwt.xml file :

```xml
	<inherits name='fr.mncc.gwttoolbox.authenticate.authenticate'/>
```

Example : securing JSON-RPC Web services
========================================

* Create Web service interface :

```java
    /**
     * Web services interfaces must extend SecureService.
     */
    public interface LoginService extends SecureService {
      void login(String username, String password, SecureAsyncCallback<Boolean> callback);
      void logout(SecureAsyncCallback<Boolean> callback);
    }
```

* Implement Web service :

```java
    /**
     * Public Web services (e.g. anybody is allowed to access them) must extend PublicServiceImpl.
     * Private Web services (e.g. only logged-in users are allowed to access them) must extend PrivateServiceImpl.
     * Admin Web services (e.g. only the admin is allowed to access them) must extend AdminServiceImpl.
     */
    public class LoginServiceImpl extends PublicServiceImpl implements LoginService {

      @Override
      public void login(String username, String password, SecureAsyncCallback<Boolean> callback) {
        if (username == null || username.isEmpty() || !username.equals("user")) {
          callback.onFailure(new Exception("Invalid username."));
          return;
        }
        if (password == null || password.isEmpty() || !password.equals("test")) {
          callback.onFailure(new Exception("Invalid password."));
          return;
        }
        saveCurrentUser(username, UserRoles.USER);  // Identify user as logged-in
        callback.onSuccess(true);
      }

      @Override
      public void logout(SecureAsyncCallback<Boolean> callback) {
        removeCurrentUser();    // Disconnect user
        callback.onSuccess(true);
      }
    }
```

* Add Web service to your WEB.xml file :

```xml
    <servlet>
        <servlet-name>loginService</servlet-name>
        <servlet-class>package_name.server.LoginServiceImpl</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>loginService</servlet-name>
        <url-pattern>/module_name/loginService</url-pattern>
    </servlet-mapping>
```

* Call Web service from the client as usual :

```java
    final LoginService loginService_ = GWT.create(LoginService.class);
    final SecurityController securityController_ = new SecurityController();

    @Override
    public void onModuleLoad() {

        ...
        securityController_.registerService(loginService_, "loginService");
        ...

        loginService_.login("user", "test", new SecureAsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                // TODO : process error
            }

            @Override
            public void onSuccess(Boolean isOk) {
                // TODO : execute action on login success
            }
        });
    }
```

Example : securing AJAX routes
==============================

```java
    final SecurityController securityController_ = new SecurityController();

    @Override
    public void onModuleLoad() {
        ...
        securityController_.registerRoute("login", AccessLevels.PUBLIC);
        securityController_.registerRoute("home", AccessLevels.PUBLIC);
        securityController_.registerRoute("myspace", AccessLevels.PRIVATE);
        securityController_.registerRoute("dashboard", AccessLevels.ADMIN);
        ...
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {

        // Check user role against route access level
        if (securityManager_.isRedirectionAllowed(event.getValue())) {
            // TODO : redirect the user
        }
        else {
            // TODO : display an error message
        }
    }
```

Example : using gwt-authenticate in conjunction with gwt-router
===============================================================

First, add [gwt-router](https://github.com/MNCC/gwt-router) to your project dependencies. Then, create a new
**SecureRoute.java** file :

```java
    public abstract class SecureRoute extends Route {

        private final SecurityController securityController_;

        public SecureRoute(String name, SecurityController securityController, int accessLevel) {
            super(name);
            securityController_ = securityController;
            securityController_.registerRoute(name, accessLevel);
        }

        @Override
        public final void enter(String arguments) {
            if (securityController_.isRedirectionAllowed(getName())) {
                onRedirectionAllowed(arguments);
            }
            else {
                onRedirectionNotAllowed(arguments);
            }
        }

        protected abstract void onRedirectionAllowed(String arguments);

        protected abstract void onRedirectionNotAllowed(String arguments);
    }
```

Then, for each route, implement **onRedirectionAllowed()** and **onRedirectionNotAllowed()**.
