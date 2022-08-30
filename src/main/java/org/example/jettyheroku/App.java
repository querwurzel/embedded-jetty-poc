package org.example.jettyheroku;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) throws Exception {

        final ContextHandler context = new ContextHandler("/hello");
        context.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
                response.getWriter().println("Hello " + request.getRemoteAddr() + "!");

                response.getWriter().println("Current time: " + LocalDateTime.now());

                baseRequest.setHandled(true); // important!
            }
        });

        final String port = System.getenv().getOrDefault("PORT", "8888");
        final Server server = new Server(Integer.parseInt(port));
        server.setHandler(context);
        server.start();
        server.join();
    }
}
