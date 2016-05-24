<%-- 
    Document   : Cate
    Created on : 2015-9-30, 22:29:25
    Author     : Administrator
--%>


<%@page import="java.util.Iterator"%>
<%@page import="com.Shop.beans.Cate"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List cates = (List) request.getAttribute("cates");
        %>
        <table align="center" border="1">
            <%
                Iterator ic = cates.iterator();
                if(ic!=null){
                while (ic.hasNext()) {
                    Cate cate = (Cate) ic.next();
            %>
            <tr>
                <td><a href="<%=request.getContextPath()%>/Cate/<%=cate.getId()%>"><%=cate.getName()%></a></td>
            </tr>

            <%
                }
             }
            %>
        </table>
    </body>
</html>
