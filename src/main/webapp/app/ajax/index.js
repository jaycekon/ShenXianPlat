$(function() {
	$(".btn").click(function() {
		$.ajax({
			type: "get",
//			后台url
			url: "test",
			dataType: "json",
			contentType:"application/json;charset=UTF-8",
			success: function(result) {
              console.log(result);
			},
			error: function() {
				alert("服务器出错");
			}
		});
	});
})