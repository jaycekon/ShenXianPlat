app.controller('CartController',function($scope){
    $scope.items = [
        {name: "新鲜小黄鱼特惠", num: 1, price: 199.00,origPrice: 223.00,imgsrc:'source/img/index/goods008.jpg'},
        {name: "豆腐干2kg装", num: 1, price: 139.00,origPrice: 243.00,imgsrc:'source/img/index/goods009.jpg'},
        {name: "优质牛肉特价", num: 1, price: 10.00,origPrice: 23.00,imgsrc:'source/img/index/goods007.jpg'},
    ];
    $scope.toggle = function($event){
        var sign = $event.target.innerHTML;
        if(sign === '编辑'){
            $event.target.innerHTML = '完成';
            $scope.editShow = true;
        }else{
            $event.target.innerHTML = '编辑';
            $scope.editShow = false;
        }
    }
    //计算总价格
    $scope.totalPrice = function () {
        var total = 0, i,
            len = $scope.items.length;
        for (i = 0; i < len; i++) {
            total += $scope.items[i].price * $scope.items[i].num;
        }
        return total;
    };
    $scope.remove = function (index) {
        $scope.items.splice(index, 1);
    }
    $scope.AddNum = function(index){
        $scope.items[index].num ++ ;
    }
    $scope.SubNum = function(index){
        if($scope.items[index].num > 1){
            $scope.items[index].num -- ;
        }else if($scope.items[index].num === 1){
            $scope.items.splice(index, 1);
        }
    }
});
