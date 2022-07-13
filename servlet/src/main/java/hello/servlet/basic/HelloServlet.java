package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// /hello를 만나면 위 servlet을 호출하고 위 servlet은 service메서드를 호출함
@WebServlet(name = " helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //http://localhost:8080/hello?username=Lim
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //응답 헤더
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        //응답 body
        response.getWriter().write("hello" + username);

    }
}
