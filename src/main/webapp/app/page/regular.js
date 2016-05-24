var myShopping = angular.module("myshopping", ['ngRoute']);

//myShopping.config(function($routeProvider){
//	$routeProvider.otherwise({
//		templateUrl:"shopcar.html",
//		controller: 'shopcar'
//	})
//});

myShopping.controller("shopcar", ["$scope",
	function($scope) {

		//商品信息
		$scope.goods = [{

			msg: "创意迷你包邮",
			imgId: "1",
			detailMsg: "颜色分类：灰色",
			price: "4.90",
			count: "2"
		}, {
			msg: "巧克力精装",
			imgId: "2",
			detailMsg: "颜色分类：灰色",
			price: "10.90",
			count: "1"
		}, {
			msg: "手机防水袋",
			imgId: "3",
			detailMsg: "颜色分类：灰色",
			price: "9.90",
			count: "1"
		}];
		//删除商品信息
		$scope.remove = function(index) {
			$scope.goods.splice(index, 1);
		};
		$scope.change = function(index, num) {
			if ($scope.goods[index].count === 0 && num === 1) {
				alert("数量不能少于0");
			} else {
				$scope.goods[index].count -= num;
			}
		};
		//全选功能
		
	}
]);

myShopping.controller("shopdisplay", ["$scope",

	function($scope) {
		$scope.message = [{
			title: "移动充电电源",
			code: "10001",
			descri: "充电电源，最新款，最精致的手机",
			price: "5000",
			thumbnail: "shop1.jpg"
		}, {
			title: "潮流男裤",
			code: "20001",
			descri: "与型男更配哦",
			price: "1000",
			thumbnail: "shop2.jpg"
		}];
		$scope.addTocar = function addTocar(item){
			if(!item.code){
				return;
			}
			
		}
	}

]);

//myShopping.Controllers("register",["$scope",
//   
//  
//]);
