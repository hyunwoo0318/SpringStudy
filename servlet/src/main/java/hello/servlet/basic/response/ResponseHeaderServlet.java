package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //응답 메세지의 상태 코드를 적어줌 [status-line]
        res.setStatus(HttpServletResponse.SC_OK);

        //[response-header]
        res.setHeader("Content-type", "text/plain");
        res.setHeader("Cache-Control", "no-cash, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        res.setHeader("my-header", "hello");

        PrintWriter writer = res.getWriter();
        writer.println("ok");

    }
}
