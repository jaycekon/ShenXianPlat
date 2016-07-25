<%-- 
    Document   : upload
    Created on : 2015-9-9, 13:04:58
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
        <form action="<%=request.getContextPath()%>/upload" method="POST" enctype="multipart/form-data">
            选择图片<input type="file" name="file"/>
            <input type="submit" value="提交"/>
        </form>
    </body>
</html>
