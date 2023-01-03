package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // MyView를 반환!
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    // 각 컨트롤러는 복잡한 dispatcher.forward()를 직접 생성해서 호출하지 않아도 된다!
    // 단순히 MyView 객체를 생성하고 거기에 뷰 이름만 넣고 반환하면 된다!
}
