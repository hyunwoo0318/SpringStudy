package hello.servlet.Web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        RequestDispatcher dis = req.getRequestDispatcher(viewPath);
        dis.forward(req, res);
    }

    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        modelToRequestAttribute(model, req);
        RequestDispatcher dis = req.getRequestDispatcher(viewPath);
        dis.forward(req, res);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
        model.forEach((key, value) -> req.setAttribute(key, value));
    }
}
