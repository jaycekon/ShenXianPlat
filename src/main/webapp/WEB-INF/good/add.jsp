<%-- 
    Document   : add
    Created on : 2015-9-10, 18:43:17
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="<%=request.getContextPath()%>/add/Cate" method="POST">
            类别：<input type="text" name="Cate_name"/>
            <input type="submit" value="添加"/>
        </form>
    </body>
</html>
