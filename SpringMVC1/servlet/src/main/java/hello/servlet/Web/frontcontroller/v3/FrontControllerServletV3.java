package hello.servlet.Web.frontcontroller.v3;

import hello.servlet.Web.frontcontroller.ModelView;
import hello.servlet.Web.frontcontroller.MyView;
import hello.servlet.Web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.Web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.Web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//urlPatterns -> /front-controller/v1/이 있는 모든 주소들은 위 servlet을 거치게됨
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    //맵핑 정보
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = req.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null){
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap을 넘겨줘야함
        Map<String, String> paramMap = createParamMap(req);

        //여기까지는 논리이름만 얻음 -> viewresolver를 통해 물리 주소를 얻어야함v
        ModelView mv = controller.process(paramMap);
        String viewName = mv.getViewName();

        MyView view = viewResolver(viewName);
        view.render(mv.getModel(),req, res);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    //req의 모든 원소들을 가지고 map을 만듬
    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
