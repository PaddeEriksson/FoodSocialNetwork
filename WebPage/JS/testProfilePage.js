var profilePage = angular.module('UserInfo');

profilePage.controller('profilePage',  function($scope, $http, $timeout){
	//This sould be removed
	$scope.userprofile = {};
	$scope.session = sessionStorage.whatever;
	$scope.userprofile = sessionStorage.profileEmail;
	
	$scope.FrInfo = { Username: sessionStorage.FriendUsername, Email:sessionStorage.FriendEmail, 
						Country:'sessionStorage.FriendCountry'};
	//TODO update to server IP
	$scope.init=function()
	{
		$http({
	   		url: "http://83.254.221.239:9000/profile/" + sessionStorage.profileEmail, 
	   		method: "GET",
	   		params: {sessionID: sessionStorage.whatever},
	 	}).success(function(data) {
	 		$scope.email = data.email;
	 		$scope.country = data.country;
	 		$scope.username = data.username;
	 		console.log($scope.username);
	 	});
	 };
	$timeout($scope.init);
});