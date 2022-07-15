package hello.servlet.Web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // 위 경로로 servlet->jsp로 넘겨줌 - 경로설정 단계
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        //위 forward함수로 진짜로 넘어감
        dispatcher.forward(req, res);

    }
}
