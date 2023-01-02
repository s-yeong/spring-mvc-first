<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 자바 코드 넣을 수 있음
    // request, response 사용 가능 (JSP도 서블릿으로 자동 변환됨)
    MemberRepository memberRepository = MemberRepository.getInstance();

    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username<%=member.getUsername()%></li>
    <li>age<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
