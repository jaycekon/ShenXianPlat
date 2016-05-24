<%-- 
    Document   : loginUser
    Created on : 2015-9-9, 0:45:03
    Author     : Administrator
--%>

<%@page import="com.Shop.beans.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="<%=request.getContextPath()%>/login" method="POST">
            账号:<input type="text" name="phone"/>
            密码:<input type="password" name="password"/>
            <input type="submit" value="登录"/>
            
        </form>
        <%
            User user=(User)request.getAttribute("loginUser");
            if(user!=null)
                System.out.println(user.getPhone());
        %>
    </body>
</html>
