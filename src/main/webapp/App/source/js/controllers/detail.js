app.controller('DetailController',function($scope){
    $scope.imgs = [
        {imagesrc:'source/img/detail/1.jpg'},
        {imagesrc:'source/img/detail/3.jpg'},
        {imagesrc:'source/img/detail/4.jpg'},
    ];
    $scope.detail = {
        title:'纯正天然健康果蔬系列之有机菠菜',
        price:'¥136',
        PrePrice:'¥389',
        num:'5',
        weigth:'750g',
        introduce:'菠菜又名波斯菜、赤根菜、鹦鹉菜等，属苋科藜亚科菠菜属，一年生草本植物。植物高可达1米，根圆锥状，带红色，较少为白色，叶戟形至卵形，鲜绿色,少数牙齿状裂片。'
    };
    $scope.comment = [
        {
            owner:'138****3456',
            content:'还不错评论文字还不错评论文字还不错评文字还不错评论文字还不错',
            data:'2015-09-13 14:35'
        },{
            owner:'158****3556',
            content:'还不错评论文字还不错评论文字还不错评文字还不错评论文字还不错',
            data:'2015-08-13 12:35'
        },{
            owner:'139****3436',
            content:'还不错评论文字还不错评论文字还不错评文字还不错评论文字还不错',
            data:'2015-09-14 16:35'
        }
    ];
    $scope.len = function () {
        return $scope.comment.length;
    }
});
