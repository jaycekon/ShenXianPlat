<%@ page import="com.Shop.beans.OrderProduct" %>
<%@ page import="com.Shop.beans.Orders" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.Shop.Util.OrderDetailPojo" %>
<%--Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/1 0001
  Time: 下午 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>一内购-订单详情</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/app/backStage/lib/bootstrap/css/bootstrap.min.css">
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

            <%@ include file="../left.jsp" %>

            <%
                OrderDetailPojo orderPojo = (OrderDetailPojo) request.getAttribute("orderPojo");
                Orders orders = orderPojo.getOrders();
                List<OrderProduct> orderProducts = orderPojo.getOrderProducts();
            %>
            <!-- [[右边操作页面 -->
            <div class="content col-md-10 orderDetail">
                <div class="order">
                    <div class="order-statue clearfix">
                        <div class="order-statue clearfix">
                            <div class="order-number pull-left">订单号：<%=orders.getId()%>
                            </div>
                            <div class="pull-right statue">
                                <%
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
                                %>
                            </div>
                        </div>
                        <div class="receive-message text-left">
                            <div class="receive-title">收货人信息</div>
                            <div class="name"><%=orders.getUserName()%>
                            </div>
                            <div class="phone"><%=orders.getUserPhone()%>
                            </div>
                            <div class="address"><%=orders.getUserAddress()%>
                            </div>
                        </div>
                        <%
                            for (OrderProduct orderProduct : orderProducts) {


                        %>
                        <div class="product clearfix">
                            <div class="pull-left col-md-8">
                                <div class="col-md-4">
                                    <img src="<%=orderProduct.getImg()%>" alt=""/>
                                </div>
                                <div class="col-md-8 text-left">
                                    <span class="name"><%=orderProduct.getProductName()%></span><br/><br/>
                                    <span class="specific">描述：<%=orderProduct.getProductDescripes()%></span><br/>
                                </div>
                            </div>
                            <div class="pull-right width-2 text-right">
                                <span class="price">￥<%=orderProduct.getProductPrices()%></span><br/>
                                <span class="number">X <%=orderProduct.getCount()%></span><br/>

                            </div>
                        </div>
                        <%
                            }
                        %>
                        <div class="order-total clearfix text-left">
                            <div class="pull-left">
                                <div class="product-total">商品合计：￥<%=orders.getPrices()%>（共<%=orders.getCount()%>件）
                                </div>
                                <div class="freight">运费合计：￥0.0</div>
                            </div>
                            <%
                                if(orders.getStatus()==1){
                            %>
                            <div class="pull-right">
                                <%--<button class="btn btn-warning"--%>
                                        <%--onclick="window.location.href='<%=request.getContextPath()%>/sendOrder/<%=orders.getId()%>'">--%>
                                    <%--发货--%>
                                <%--</button>--%>
                                    <button type="button" class="btn btn-warning" data-toggle="modal"
                                            onclick="window.location.href='<%=request.getContextPath()%>/BackStage/SendGood?id=<%=orders.getId()%>'"
                                            data-target="#logisticInfoModal">发货</button>
                            </div>

                            <%
                            }else if(orders.getStatus()==2){
                            %>
                            <div class="product-total pull-right">未收货
                            </div>
                            <%
                                    }else if(orders.getStatus()==3){
                                        %>
                            <div class="product-total pull-right">已收货
                            </div>
                            <%
                            }else if(orders.getStatus()==4){%>
                            <div class="product-total pull-right">已取消
                            </div>
                            <%
                                }
                            %>
                        </div>


                        <div class="order-time text-left">
                            <div class="placeOrder">下单时间：<%=orders.getSetDate()%>
                            </div>
                            <div class="payOrder">付款时间：<%
                                if (orders.getPayDate() == null) {
                                    out.println("未付款");
                                } else {
                                    out.println(orders.getPayDate());
                                }

                            %></div>
                            <div class="sendOrder">发货时间：<%
                                if (orders.getSentDate() == null) {
                                    out.println("未发货");
                                } else {
                                    out.println(orders.getSentDate());
                                }

                            %></div>
                        </div>
                    </div>
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

</div>
</body>
</html>