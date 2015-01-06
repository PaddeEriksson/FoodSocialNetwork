var profilePage = angular.module('UserInfo');

profilePage.controller('recipePage',  function($scope, $http){
	//This sould be removed


	$scope.recipeID = sessionStorage.recipeID;
	$scope.session = sessionStorage.whatever;
	console.log($scope.recipeID);
	//TODO update to server IP
	$http({
   		url: "http://83.254.221.239:9000/recipe/" + $scope.recipeID, 
   		method: "GET",
   		params: {sessionID: sessionStorage.whatever},
 	}).success(function(data) {
 		console.log("working =ss " + data.ingridients + " " + data.error);
 		$scope.recipeTitle = data.recipe.recipeTitle;
 		$scope.username = data.recipe.creator;
 		$scope.time = data.recipe.time;
 		$scope.instructions = data.recipe.instruction;
 		$scope.category = data.recipe.category; //not in use
 		$scope.ingredients = data.ingridients;
 		$scope.comments = data.comments;
 	});



 	$scope.PostComment = function()
 	{
 		$http({
   		url: "http://83.254.221.239:9000/rateAndComment", 
   		method: "GET",
   		params: {sessionID: sessionStorage.whatever,recipeID:$scope.recipeID,rate:$scope.rate,comment:$scope.comment},
 	}).success(function(data) {
 	});
 	};

 	 $scope.AddAsFavorite = function()
 	{
 		$http({
   		url: "http://83.254.221.239:9000/addFavorite", 
   		method: "GET",
   		params: {sessionID: sessionStorage.whatever,recipeID:$scope.recipeID},
 	}).success(function(data) {
 		if(data.success)
 		{
 			alert("Favorite added");
 		}
 		else
 		{
 			console.log(data.error);
 		}
 	});
 	};
});