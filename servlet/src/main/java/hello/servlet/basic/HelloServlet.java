package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")  // localhost:8080/hello 오면 실행
// name: 서블릿 이름, urlPatterns: URL 매핑
public class HelloServlet extends HttpServlet {
    // HttpServlet은 추상 클래스, Servlet은 인터페이스
    // => WAS들이 서블릿 표준 스펙을 구현

    // HTTP 요청을 통해 매핑된 URL이 호출되면 서블릿 컨테이너는 service 메서드 호출
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 서블릿 컨테이너(WAS)가 request, response 객체 만들어서 서블릿에 넘겨줌
        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);

        String username = req.getParameter("username");
        System.out.println("username = " + username);

        // Header
        resp.setContentType("text/plain");  // 단순 문자
        resp.setCharacterEncoding("utf-8");
        // Body
        resp.getWriter().write("hello " + username);
    }
}
