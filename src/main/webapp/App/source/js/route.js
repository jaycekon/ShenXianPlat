var app = angular.module('myApp',['ngRoute']);

    app.config(['$routeProvider', function($routeProvider){
        $routeProvider
            .when('/',{
                templateUrl:'source/tmp/index.html',
                controller:'IndexController'
            })
            .when('/login',{
                templateUrl:'source/tmp/login.html'
            })
            .when('/register',{templateUrl:'source/tmp/register.html'})
            .when('/cart',{
                templateUrl:'source/tmp/cart.html',
                controller:'CartController'
            })
            .when('/detail',{
                templateUrl:'source/tmp/detail.html',
                controller:'DetailController'
            })
            .when('/search',{templateUrl:'source/tmp/search.html'})
            .when('/order',{templateUrl:'source/tmp/order.html'})
            .when('/confirm_order',{templateUrl:'source/tmp/confirm_order.html'})
            .when('/address',{templateUrl:'source/tmp/address.html'})
            .when('/user',{templateUrl:'source/tmp/user.html'})
            .otherwise({
                redirectTo:'/'
            });
    }]);