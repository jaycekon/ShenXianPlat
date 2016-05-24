<%-- 
    Document   : cart
    Created on : 2015-9-30, 23:25:04
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
        <form action="<%=request.getContextPath()%>/Cart/<%=request.getAttribute("id")%>" method="post">
            添加的商品名字:<input type="text" name="name"/>
            <input type="submit" value="购买"/>
            
        </form>
    </body>
</html>
