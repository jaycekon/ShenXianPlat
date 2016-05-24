<%-- 
    Document   : update
    Created on : 2015-9-9, 1:09:21
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
        <%
            User user = (User) request.getAttribute("loginUser");
        %>
        <form actiopn="<%=request.getContextPath()%>/update" method="POST" enctype="multipart/form-data">
            用户账号:<%=user.getPhone()%>
            用户密码：<input type="password" name="password" value="<%=user.getPassword()%>"/><br>
            用户昵称：<input type="text" name="nickname" value="<%=user.getPhone()%>"/><br>
            用户性别：<input type="text" name="sex" value="<%=user.getSex()%>"/><br>
            用户邮箱：<input type="text" name="Email" value="<%=user.getEmail()%>"/><br>
            用户头像：<input type="file" name="file" /><br>
            <input type="submit" value="修改用户信息"/><br>
            <%
            if(user.getImg()!=null)
                {
            %>
            <img src="<%=request.getContextPath()%>/app/img/user/<%=user.getImg()%>"/>
            <%
                }
            %>
        </form>
    </body>
</html>
