var profilePage = angular.module('UserInfo');

profilePage.controller('profilePage',  function($scope, $http, userProfileID){
	//This sould be removed
	sessionStorage.whatever = "2147f892-b4c2-485e-9866-ded50c6381e3";
	userProfileID.set("this");

	console.log(userProfileID.get());
	$scope.session = sessionStorage.whatever;
	$scope.userprofile = userProfileID.get();
	console.log($scope.username);
	//TODO update to server IP
	$http({
   		url: "http://localhost:8080/profile/" + userProfileID.get(), 
   		method: "GET",
   		params: {sessionID: sessionStorage.whatever},
 	}).success(function(data) {
 		console.log("working =ss " + data + " " + data.error);
 		$scope.email = data.email;
 		$scope.country = data.country;
 		$scope.username = data.username;
 	});
});