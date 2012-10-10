package ioke.lang.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class IokeServlet extends HttpServlet {

    @Override public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ioke.lang.Runtime runtime;
        try {
            runtime = new ioke.lang.Runtime(response.getWriter(), request.getReader(), response.getWriter());
            runtime.init();
            runtime.evaluateStream(
                    new BufferedReader(new InputStreamReader(new ByteArrayInputStream("\"Hello World!\" println".getBytes())))
            );
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
