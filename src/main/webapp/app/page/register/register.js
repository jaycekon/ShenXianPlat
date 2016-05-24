$(function(){
		$("#footer").load("Shop/app/components/footer/footer.html");
		function sendmsg() {
			var mobilenum = $(".username").val(),
				nickname = $(".nickname").val(),
			    psw = $(".password").val();
			var datas = {
				"phone":mobilenum,
				"nickname":nickname,
				"password":psw
			}
			$.ajax({
			type: "POST",
			data: datas,
			url: "/Shop/register",
			dataType: "json",
			success: function(data) {
				showtip("用户名错误");
				//showMask();
				//3秒跳转至主页
				//setTimeout(function() {
				//	window.location.href = "/Shop/index.html";
				//}, 3000)
			},
			error: function() {
				alert("无法获得资料233");
			}
		});
		}

//显示提示信息   传入的g为提示的信息
	function showtip(g) {
		$(".hint").text(g);
		$(".hint").removeClass("hide fadeOutUp").addClass("fadeInDown");
//过3秒自动隐藏
		setTimeout(function() {
			$(".hint").removeClass("fadeInDown").addClass("fadeOutUp")
		}, 3000)
	}
	//显示遮罩层
	function showMask(){
		$("#mask").removeClass("hide");
	}
//滑动验证插件
	$.fn.drag = function(options){
		var x, drag = this, isMove = false, defaults = {
		};
		var options = $.extend(defaults, options);
		//添加背景，文字，滑块
		var handler = drag.find('.handler');
		var drag_bg = drag.find('.drag_bg');
		var text = drag.find('.drag_text');
		var maxWidth = drag.width() - handler.width();  //能滑动的最大间距

		//鼠标按下时候的x轴的位置
		handler.mousedown(function(e){
			isMove = true;
			x = e.pageX - parseInt(handler.css('left'), 10);
		});

		//鼠标指针在上下文移动时，移动距离大于0小于最大间距，滑块x轴位置等于鼠标移动距离
		$(document).mousemove(function(e){
			var _x = e.pageX - x;
			if(isMove){
				if(_x > 0 && _x <= maxWidth){
					handler.css({'left': _x});
					drag_bg.css({'width': _x});
				}else if(_x > maxWidth){  //鼠标指针移动距离达到最大时清空事件
					handler.css({'left': maxWidth});
					drag_bg.css({'width': maxWidth});
					dragOk();
				}
			}
		}).mouseup(function(e){
			isMove = false;
			var _x = e.pageX - x;
			if(_x < maxWidth){ //鼠标松开时，如果没有达到最大距离位置，滑块就返回初始位置
				handler.css({'left': 0});
				drag_bg.css({'width': 0});
			}
		});

		//清空事件
		function dragOk(){
			handler.find("span").addClass('fa-check-circle')
				.css({'color': 'rgb(150, 211, 239)'}).removeClass('fa-angle-double-right');
			text.text('验证通过');
			drag.css({'color': '#fff'});
			handler.unbind('mousedown');
			$(document).unbind('mousemove');
			$(document).unbind('mouseup');
		}
	};
		$('#drag').drag();
		$(".re-submit").click(sendmsg);
})
