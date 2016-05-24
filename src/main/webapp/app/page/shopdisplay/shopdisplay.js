var shopmsg = {
	"shopMsg": [{
		"title": "珍享 澳洲进口脐橙 澳大利亚橙子（单果重约220g）12个装",
		"price": "￥55",
		"commoNum": "3320"
	}]
}

var shopimg = {
		"detailImg": [{
			"src": "/Shop/app/components/img/shop/shop1-1.jpg"
		}, {
			"src": "/Shop/app/components/img/shop/shop1-2.jpg"
		}, {
			"src": "/Shop/app/components/img/shop/shop1-3.jpg"
		}, {
			"src": "/Shop/app/components/img/shop/shop1-4.jpg"
		}, {
			"src": "/Shop/app/components/img/shop/shop1-5.jpg"
		}]
	}
	//每页7条信息
var detailcommo = {
	"userMsg": [{
		"img": "/Shop/app/components/img/user/user3.png",
		"name": "Lucky",
		"data": "2015-10-09",
		"commomsg": "这件商品不错不错"
	}, {
		"img": "/Shop/app/components/img/user/user1.png",
		"name": "cpp",
		"data": "2015-10-09",
		"commomsg": "好评！！！"
	}, {
		"img": "/Shop/app/components/img/user/user2.png",
		"name": "cxs",
		"data": "2015-10-09",
		"commomsg": "同事都说好"
	}, {
		"img": "/Shop/app/components/img/user/user2.png",
		"name": "cxs",
		"data": "2015-10-09",
		"commomsg": "同事都说好"
	}, {
		"img": "/Shop/app/components/img/user/user1.png",
		"name": "cpp",
		"data": "2015-10-09",
		"commomsg": "好评！！！"
	}, {
		"img": "/Shop/app/components/img/user/user2.png",
		"name": "cxs",
		"data": "2015-10-09",
		"commomsg": "同事都说好"
	}, {
		"img": "/Shop/app/components/img/user/user2.png",
		"name": "cxs",
		"data": "2015-10-09",
		"commomsg": "同事都说好"
	}]
}
var detext = {
	"detailtext": [{
		"descri": "商品名称：珍享澳洲进口脐橙"
	}, {
		"descri": "商品产地：澳大利亚"
	}, {
		"descri": "配送区域：北京，上海"
	}, {
		"descri": "品种：橙子"
	}, {
		"descri": "商品毛重：3.0kg"
	}, ]
}

var deimg = {
	"detailimg": [{
		"desrc": "/Shop/app/components/img/shop/shopdescri1-1.jpg"
	}, {
		"desrc": "/Shop/app/components/img/shop/shopdescri1-2.jpg"
	}, {
		"desrc": "/Shop/app/components/img/shop/shopdescri1-3.jpg"
	}, {
		"desrc": "/Shop/app/components/img/shop/shopdescri1-4.jpg"
	}, ]
}

window.shop = {};
$(function() {
	var applyJuicer = function($e, data) {
		var tmplId = $e.attr('tmpl');
		var tmpl = $('#' + tmplId).html();
		$e.html(juicer(tmpl, data));
	}
	$("#top").load("/Shop/app/components/header/top.html");

	$(window).scroll(function() {
		var one = $(".scroll").find("li").eq(0),
		    two = $(".scroll").find("li").eq(1);
		var target = $(".scroll"),
			scro = $(window).scrollTop();
		if (scro >= 600 && scro <= 3700) {
			target.addClass("fixtop");
			one.addClass("active")
              .siblings().removeClass("active");
		} else if(scro < 600){
			target.removeClass("fixtop");
		}else if(scro > 3700){
			two.addClass("active")
              .siblings().removeClass("active");
		}
	});

	window.shop.anima = function() {
		//	显示评论信息	
		function showCommomMsg() {
				applyJuicer($('#common-data'), {
					list: detailcommo.userMsg
				});
			}
			//显示商品信息		

		function showShopMsg() {
				applyJuicer($('#shop-data'), {
					list: shopmsg.shopMsg
				});
			}
			//显示商品小图片

		function showshopimg() {
				applyJuicer($('#img-data'), {
					list: shopimg.detailImg
				});
			}
			//显示商品文字描述

		function showdetailtext() {
				applyJuicer($('#detext-data'), {
					list: detext.detailtext
				});
			}
			//		显示商品图片描述

		function showdetailimg() {
				applyJuicer($('#deimg-data'), {
					list: deimg.detailimg
				});
			}
			//加载数据
		showShopMsg();
		showCommomMsg();
		showshopimg();
		showdetailtext();
		showdetailimg();
		//		选择地区显隐
		function show() {
				var $hPlace = $(".dropdownPlace");
				if ($hPlace.hasClass("hide")) {
					$hPlace.removeClass("hide");
				} else {
					$hPlace.addClass("hide");
				}
			}
			//      点击小图标显示大图标

		function eachShow() {
				var Fsrc = $(this).attr("src");
				var Lsrc = $(".target")
					.attr("src", Fsrc);
			}
			//      数量改变

		function changeNum() {
			var val = $(this).find("button").text(),
				show = $(this).parent().find("input"),
				showVal = show.val();

			if (val === "-" && showVal != 0) {
				showVal = showVal - 1;
				show.val(showVal);
			} else if (val === "+") {
				showVal = parseInt(showVal) + 1;
				show.val(showVal);
			}
		}

		function toggletextimg() {
			$(this).addClass("active")
				.siblings().removeClass("active");

			var inner = $(this).find("a").text();
			if (inner === "商品评价") {
				$(".detail-intro").addClass("hide");
			} else if (inner === "宝贝详情") {
				$(".detail-intro").removeClass("hide");
			}
		}


		//		选择类型
		//      function choseType() {
		//      	var s = $("this").text();
		//      	console.log(s);
		//      	$("this").addClass("active");
		//      }
		$("#img-data").find("img").click(eachShow);
		$(".activePlace").click(show);
		$(".num").find("span").click(changeNum);
		$(".scroll").find("li").click(toggletextimg);
		//		$(".beChoose").click(choseType);
	}

	window.shop.anima();
})