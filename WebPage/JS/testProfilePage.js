var profilePage = angular.module('UserInfo');

profilePage.controller('profilePage',  function($scope, $http){
	//This sould be removed
	$scope.userprofile = {};
	$scope.session = sessionStorage.whatever;
	$scope.userprofile = sessionStorage.profileEmail;
	//TODO update to server IP
	$http({
   		url: "http://83.254.221.239:9000/profile/" + sessionStorage.profileEmail, 
   		method: "GET",
   		params: {sessionID: sessionStorage.whatever},
 	}).success(function(data) {
 		$scope.email = data.email;
 		$scope.country = data.country;
 		$scope.username = data.username;
 	});
});