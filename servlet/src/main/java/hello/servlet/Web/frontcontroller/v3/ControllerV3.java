package hello.servlet.Web.frontcontroller.v3;

import hello.servlet.Web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    //servlet에 종속적이지 않음
    ModelView process(Map<String, String> paramMap);
}
