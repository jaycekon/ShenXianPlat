<%-- 
    Document   : addGood
    Created on : 2015-9-10, 19:02:17
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
        <form action="<%=request.getContextPath()%>/add/Good" method="POST" enctype="multipart/form-data">
            商品名：<input type="text" name="Good_name"/><br>
            商品简介：<input type="text" name="intro"/><br>
            商品详细介绍:<input type="text" name="produce"/><br>
            商品数量：<input type="number" name="num" /><br>
            商品价格:<input type="text" name="price"/><br>
            商品种类:<input type="text" name="Cate_name"/><br>
            商品简介图片:<input type="file" name="goods_file"/><br>
            商品详细图片:<input type="file" name="goods_files"/><br>
            商品详细图片:<input type="file" name="goods_files"/><br>
            商品详细图片:<input type="file" name="goods_files"/><br>
            <input type="submit" value="添加"/>
        </form>
    </body>
</html>
