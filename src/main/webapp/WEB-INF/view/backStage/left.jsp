<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/1 0001
  Time: 下午 4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- [[ 左边导航 -->
<ul class="col-md-3 col-lg-2 navigation" aria-expanded="true">

    <li>
        <div class="navItem"><span class="glyphicon glyphicon-tag"></span>商品管理</div>
        <ul class="subNav">
            <li><a href="<%=request.getContextPath()%>/BackStage/ListProduct">商品列表</a></li>
            <li><a href="<%=request.getContextPath()%>/BackStage/AddProduct">发布商品</a></li>
        </ul>
    </li>
    <li>
        <div class="navItem"><span class="glyphicon glyphicon-file"></span>订单管理</div>
        <ul class="subNav">
            <li><a href="<%=request.getContextPath()%>/BackStage/ListOrders">订单列表</a></li>
        </ul>
    </li>
</ul> <!-- 左边导航]] -->
