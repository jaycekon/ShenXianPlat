<%-- 
    Document   : list
    Created on : 2015-9-14, 21:06:07
    Author     : Administrator
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.Shop.beans.Good"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<Good> goods=(List)request.getAttribute("goods");
            if(goods!=null){
            Iterator Igoods=goods.listIterator();
            while(Igoods.hasNext()){
                Good good=(Good)Igoods.next();
                %>
                <img src="<%=request.getContextPath()%>/app/img/<%=good.getImg()%>"<br>
                <%
            }
        }
        %>
    </body>
</html>
