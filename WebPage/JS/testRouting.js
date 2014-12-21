var app = angular.module('app', []); 
console.log("What?");

//Define Routing for app
//Uri /AddNewOrder -> template add_order.html and Controller AddOrderController
//Uri /ShowOrders -> template show_orders.html and Controller AddOrderController
app.config(['$routeProvider',
  function($routeProvider) {
    console.log("hello");
    $routeProvider.
      when('/AddNewOrder', {
        templateUrl: '../Templates/temp.html',
        controller: 'AddOrderController'
    }).
      when('/ShowOrders', {
        templateUrl: '../Templates/temp.html',
        controller: 'ShowOrdersController'
      }).
      otherwise({
        redirectTo: '/AddNewOrder'
      });
}]);
 
 
app.controller('AddOrderController', function($scope) {
     console.log("HELLO22");
    $scope.message = 'This is Add new order screen';
     
});

app.controller('ShowOrdersController', function($scope) {
     console.log("HELLO");
    $scope.message = 'This is Show new order screen';
     
});
