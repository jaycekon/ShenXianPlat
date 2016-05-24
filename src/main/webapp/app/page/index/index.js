//配置
require.config({
	baseUrl: "../../components",
	paths: {
		"jquery": "js/jquery.min",
		"juicer": "js/juicer-min",
		"bootstrap": "js/bootstrap.min",
		"top":"header/top"
	},
	shim: {
		'juicer': {
			exports: 'juicer'
		},
		'bootstrap': {
			deps: ['jquery'],
			exports: 'bootstrap'
		}
	}
});

require(['jquery', 'juicer', 'bootstrap'], function($, juicer, bootstrap) {
	var articles = [{
		tag: '特惠',
		title: "惊！9.9元能买到这个？"
	}, {
		tag: '公告',
		title: "云农场10周年庆"
	}, {
		tag: '特惠',
		title: "华南绿色植物"
	}, {
		tag: '公告',
		title: "新品种高品质研制成功"
	}, {
		tag: '特惠',
		title: "夏日降暑特惠"
	}];
	//热门商品区域
	var goods = [{
		imgSrc: 'img/5.png'
	}, {
		imgSrc: 'img/2.png'
	}, {
		imgSrc: 'img/3.png'
	}, {
		imgSrc: 'img/4.png'
	}];


	$(function() {
		juicer.register('stress', function(data) {
			return '[' + data + ']';
		});
		var applyJuicer = function($e, data) {
				var tmplId = $e.attr('tmpl');
				var tmpl = $('#' + tmplId).html();
				$e.html(juicer(tmpl, data));
		}

		//	显示顶部和尾部区域
//	$("#top").load("Shop/app/components/header/top.html");
//	$("#footer").load("Shop/app/components/footer/footer.html");

			//	显示顶部和尾部区域
		$("#top").load("Shop/app/components/header/top.html");
		$("#footer").load("Shop/app/components/footer/footer.html");


		//显示数据
		applyJuicer($('#news-data'), {
			list: articles
		});

		applyJuicer($('#hotGoods-data'), {
			list: goods
		});


		function Nav() {}
		Nav.prototype = {
			constructor: Nav,
			//动态改变小滑块
			overMove: function() {
				var slide = $(this).parent().parent()
					.find(".nav-label");
				var nowtop = $(this).offset();
				var settop = nowtop.top - 131;
				slide.css("top", settop + "px");
			},
			outMove: function() {
				var slide = $(this).parent().parent()
					.find(".nav-label");
				slide.css("top", "0px");
			}
		}


		//动态改变小滑块
		var label = new Nav();
		$(".side-nav-container")
			.find("li").hover(
				label.overMove,
				label.outMove
			)
			//		轮播
		$('.carousel').carousel();


	})
});
//加入其它模块
//require(['top'], function(top) {
//	alert(top.add(3,4));
//});
