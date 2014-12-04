var myApp = angular.module('httpTest', []);

myApp.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);

myApp.controller('TestHttpController',  function($scope, $http){

	$scope.setInfo = { Name: ""};


	$scope.create = function() {
		console.log($scope.setInfo.Name);
		$http({
    		url: "http://193.11.186.43:9000/createAccount", 
    		method: "GET",
    		params: {username: $scope.setInfo.Name, password: "pass", email: $scope.setInfo.Name, country: "ww"}
 		}).success(function(data) {
 			console.log("working = " + data.success + " " + data.error);
 		});
	}
});