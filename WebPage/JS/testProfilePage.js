var app = angular.module('profileApp', []);

app.controller('profilePage',  function($scope, $http){

	sessionStorage.whatever = "631339e9-d377-4bbf-b5f6-9bcbf6775a41";
	sessionStorage.userprofile = "ewew";
	$scope.session = sessionStorage.whatever;
	$scope.userprofile = sessionStorage.userprofile;
	console.log($scope.username);
	$http({
   		url: "http://localhost:9000/profile/" + $scope.userprofile, 
   		method: "GET",
   		params: {sessionID: sessionStorage.whatever},
 	}).success(function(data) {
 		console.log("working =ss " + data + " " + data.error);
 		$scope.email = data.email;
 		$scope.country = data.country;
 		$scope.username = data.username;
 	});
});