package hello.servlet.Web.frontcontroller.v2;

import hello.servlet.Web.frontcontroller.MyView;
import hello.servlet.Web.frontcontroller.v1.ControllerV1;
import hello.servlet.Web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.Web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.Web.frontcontroller.v1.controller.MemberSaveControllerV1;
import hello.servlet.Web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.Web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.Web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//urlPatterns -> /front-controller/v1/이 있는 모든 주소들은 위 servlet을 거치게됨
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    //맵핑 정보
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = req.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI);
        if(controller == null){
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(req, res);
        view.render(req, res);

    }
}
