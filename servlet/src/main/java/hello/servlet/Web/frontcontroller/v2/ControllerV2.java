package hello.servlet.Web.frontcontroller.v2;

import hello.servlet.Web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    //MyView를 return하는게 차이점
    MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
