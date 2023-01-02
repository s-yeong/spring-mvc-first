package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    // 컨트롤러!
    // 클라이언트 -> /servlet-mvc/members/new-form 요청 ( 서버 호출 )


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 서버 내에서 서블릿 호출 후 -> JSP 호출
        String viewPath = "/WEB-INF/views/new-form.jsp"; // WEB-INF => 외부에서 JSP 호출X
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);  // JSP 호출 (다른 서블릿이나 JSP로 이동할 수 있는 기능)
    }
}
