package com.thoughtworks.rosetta;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

public class Server{
    public static void main(String[] args){
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(8080);

        ProtectionDomain domain = Server.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        HandlerCollection handlers = new HandlerCollection();

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setDescriptor(System.getProperty("root", location.toExternalForm()) + "WEB-INF/web.xml");
        webapp.setServer(server);
        webapp.setWar(System.getProperty("root", location.toExternalForm()));

        handlers.setHandlers(new Handler[]{webapp});
        server.setHandler(handlers);

        try {
            server.setStopAtShutdown(true);
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}