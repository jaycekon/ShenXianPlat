$(document).ready(function(){
    var mySwiper = new Swiper('#slide',{
        autoplay:5000,
        visibilityFullFit : true,
        loop:true,
        pagination : '.pagination',
    });

    ////$(".tab_proList dd").eq(0).show().siblings(".tab_proList dd").hide();
    ////$(".tab_proList dt a").eq(0).addClass("currStyle");
    //$(".tab_proList dt a").click(function(){
    //    var liindex = $(".tab_proList dt a").index(this);
    //    $(this).addClass("currStyle").siblings().removeClass("currStyle");
    //    $(".tab_proList dd").eq(liindex).fadeIn(150).siblings(".tab_proList dd").hide();
    //});

//    $(".addToCart").click(function(){
//        $(".hoverCart a").html(parseInt($(".hoverCart a").html())+1);/*悬浮购物车+1*/
//        var shopOffset = $(".hoverCart").offset();
//        var cloneDiv = $(this).parent().siblings(".goodsPic").clone();
//        var proOffset = $(this).parent().siblings(".goodsPic").offset();
//        cloneDiv.css({ "position": "absolute", "top": proOffset.top, "left": proOffset.left });
//        $(this).parent().siblings(".goodsPic").parent().append(cloneDiv);
//        cloneDiv.animate({
//            width:0,
//            height:0,
//            left: shopOffset.left,
//            top: shopOffset.top,
//            opacity:1
//        },"slow");
//    });
});

app.controller('IndexController',function($scope){
    $scope.sale = [
        {title: "新鲜生菜两斤装特惠", price: 3.90,origPrice: 5.90,imgsrc:'source/img/index/goods001.jpg'},
        {title: "红萝卜3斤装", price: 8.90,origPrice:12.90,imgsrc:'source/img/index/goods002.jpg'},
        {title: "西红柿5斤装", price: 6.90,origPrice: 9.90,imgsrc:'source/img/index/goods003.jpg'},
    ];
    $scope.hot = [
        {title: "西红柿5斤装", price: 6.90,origPrice: 9.90,imgsrc:'source/img/index/goods004.jpg'},
        {title: "西红柿5斤装", price: 6.90,origPrice: 9.90,imgsrc:'source/img/index/goods005.jpg'},
        {title: "西红柿5斤装", price: 6.90,origPrice: 9.90,imgsrc:'source/img/index/goods006.jpg'},
    ];
    $scope.new = [
        {title: "西红柿5斤装", price: 6.90,origPrice: 9.90,imgsrc:'source/img/index/goods007.jpg'},
        {title: "西红柿5斤装", price: 6.90,origPrice: 9.90,imgsrc:'source/img/index/goods008.jpg'},
        {title: "西红柿5斤装", price: 6.90,origPrice: 9.90,imgsrc:'source/img/index/goods009.jpg'},
    ];
    $scope.hotShow = true;
    $scope.newShow = false;
    $scope.saleShow = false;
    $scope.toggleList = function($event){
        if($event.target.innerHTML==='热销'){
            $scope.hotShow = true;
            $scope.newShow = false;
            $scope.saleShow = false;
        }else if($event.target.innerHTML==='新品'){
            $scope.hotShow = false;
            $scope.newShow = true;
            $scope.saleShow = false;
        }else{
            $scope.hotShow = false;
            $scope.newShow = false;
            $scope.saleShow = true;
        }
    }
});
