$(function() {
	var applyJuicer = function($e, data) {
		var tmplId = $e.attr('tmpl');
		var tmpl = $('#' + tmplId).html();
		$e.html(juicer(tmpl, data));
	}

	var shopmsg = {
		"shopMsg": [{
			"title": "珍享 澳洲进口脐橙 澳大利亚橙子（单果重约220g）12个装",
			"price": "￥55",
			"commoNum": "3320"
		},{
			"title": "珍享 澳洲进口脐橙 澳大利亚橙子（单果重约220g）12个装",
			"price": "￥55",
			"commoNum": "3320"
		},{
			"title": "珍享 澳洲进口脐橙 澳大利亚橙子（单果重约220g）12个装",
			"price": "￥55",
			"commoNum": "3320"
		},{
			"title": "珍享 澳洲进口脐橙 澳大利亚橙子（单果重约220g）12个装",
			"price": "￥55",
			"commoNum": "3320"
		},{
			"title": "珍享 澳洲进口脐橙 澳大利亚橙子（单果重约220g）12个装",
			"price": "￥55",
			"commoNum": "3320"
		}]
	}

	function test() {
		applyJuicer($('#shop-data'), {
			list: shopmsg.shopMsg
		});
	}
	
	$("#top").load("../../components/header/top.html");
	window.init = function() {
		test();
	}

	window.init();
})