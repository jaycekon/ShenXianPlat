<%@ page import="java.util.List" %>
<%@ page import="com.Shop.beans.Orders" %>
<%@ page import="com.Shop.beans.OrderProduct" %>
<%@ page import="com.Shop.Util.Page" %>
<%@ page import="com.Shop.Util.OrderDetailPojo" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/31 0031
  Time: 下午 4:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>农汇-订单列表</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/backStage/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/backStage/css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/app/backStage/css/order.css">

</head>
<body>

<div class="wrapper">
    <!-- [[头部 -->
    <%@ include file="../header.jsp"%><!-- 头部]] -->

    <!-- [[页面主体 -->
    <div class="mainBody container-fluid">

        <div class="row">
            <%@ include file="../left.jsp"%>


            <!-- [[右边操作页面 -->
            <div class="content col-md-10 orderManager">
                <div class="order-type text-left">
                    <a href ="<%=request.getContextPath()%>/BackStage/ListOrders?status=0"><button class="btn btn-default">未付款</button></a>
                    <a href ="<%=request.getContextPath()%>/BackStage/ListOrders?status=1"><button class="btn btn-default">已付款</button></a>
                    <a href ="<%=request.getContextPath()%>/BackStage/ListOrders?status=2"><button class="btn btn-default">未发货</button></a>
                    <a href ="<%=request.getContextPath()%>/BackStage/ListOrders?status=3"><button class="btn btn-default">已收货</button></a>
                    <a href ="<%=request.getContextPath()%>/BackStage/ListOrders?status=4"><button class="btn btn-default">已取消</button></a>
               </div>
                <!-- [[订单-->
                <%
                    List<OrderDetailPojo> orderPoJos = (List<OrderDetailPojo>)request.getAttribute("orderPojos");
                    for(OrderDetailPojo orderPoJo:orderPoJos){
                        Orders orders = orderPoJo.getOrders();
                        List<OrderProduct> orderProducts = orderPoJo.getOrderProducts();
                %>
                <div class="order">
                    <a href="<%=request.getContextPath()%>/BackStage/orderDetail?id=<%=orders.getId()%>">
                        <div class="order-statue clearfix">
                            <div class="company pull-left">订单号：<%=orders.getId()%></div>
                            <div class="pull-right contact">订单状态：<%
                                switch(orders.getStatus()){
                                    case 0: out.print("未付款");
                                        break;
                                    case 1: out.print("已付款");
                                        break;
                                    case 2: out.print("未收货");
                                        break;
                                    case 3: out.print("已收货");
                                        break;
                                    case 4: out.print("已取消");
                                        break;
                                }
                            %></div>
                        </div>
                        <%
                            for(OrderProduct orderProduct:orderProducts){
                        %>
                        <div class="product clearfix">
                            <div class="pull-left col-md-8">
                                <div class="col-md-4">
                                    <img src="<%=orderProduct.getImg()%>" alt=""/>
                                </div>
                                <div class="col-md-8 text-left">
                                    <span class="name"><%=orderProduct.getProductName()%></span><br /><br />
                                    <span class="specific">商品描述：<%=orderProduct.getProductDescripes()%></span><br />
                                </div>
                            </div>
                            <div class="pull-right width-2 text-right">
                                <span class="price">￥<%=orderProduct.getProductPrices()%></span><br /><br />
                                <span class="number">X <%=orderProduct.getCount()%></span><br />
                                <span class="status"><%
                                    switch(orderProduct.getStatus()){
                                        case 0: out.print("");
                                            break;
                                        case 1: out.print("申请退货");
                                            break;
                                        case 2: out.print("同意退货");
                                            break;
                                        case 3: out.print("买家发货");
                                            break;
                                        case 4: out.print("退货成功");
                                            break;
                                    }
                                %></span>
                            </div>
                        </div>

                        <%
                            }
                        %>
                        <div class="order-total clearfix text-left">
                            <div class="product-total">商品合计：￥<%=orders.getPrices()%>（共<%=orders.getCount()%>件）</div>

                            <div class="freight">运费合计：￥0.00</div>
                        </div>

                        <div class="ui-border-b block operateBlock">
                        </div>
                    </a>
                </div>
                <%
                    }
                %>
                <!-- 订单]]-->
                <!-- 分页]]-->
            </div>
            <!-- 右边操作页面]] -->
        </div>

    </div>
    <!-- 页面主体]] -->
</div>
<!-- wrapper]] -->

<script src="<%=request.getContextPath()%>/app/backStage/lib/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/app/backStage/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/app/backStage/js/common.js"></script>

<script>
    var num = parseInt( $('#recordNum').val() );
    $("#prevPage").click(function(){
        var suffix = num-10;
// 返回url
        var url = handleURL(suffix);
// 跳转url
        window.location.href = url;
    });

    $("#nextPage").click(function(){
        var suffix = num+10;
// 返回url
        var url = handleURL(suffix);
// 跳转url
        window.location.href = url;
    });

    // 处理url的函数
    function handleURL(suffix){    // suffix 后缀 为int类型
        var pathname = window.location.pathname.split('/');
        if(pathname.length==2){
            pathname = pathname.concat(suffix);
        }else{
            pathname.pop();
            pathname = pathname.concat(suffix);
        }
        return url = pathname.join('/');
    }
</script>

</body>
</html>