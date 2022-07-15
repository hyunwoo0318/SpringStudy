package hello.servlet.Web.frontcontroller.v4;

import hello.servlet.Web.frontcontroller.ModelView;
import hello.servlet.Web.frontcontroller.MyView;
import hello.servlet.Web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.Web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.Web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//urlPatterns -> /front-controller/v1/이 있는 모든 주소들은 위 servlet을 거치게됨
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    //맵핑 정보
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        String requestURI = req.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);
        if(controller == null){
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap을 넘겨줘야함
        Map<String, String> paramMap = createParamMap(req);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);
        view.render(model,req, res);

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
