var profilePage = angular.module('UserInfo');

profilePage.controller('recipePage',  function($scope, $http, recipeID){
	//This sould be removed


	sessionStorage.whatever = "2147f892-b4c2-485e-9866-ded50c6381e3";
	recipeID.set(1);
	$scope.recipeID = recipeID.get();
	$scope.session = sessionStorage.whatever;
	console.log($scope.username);
	//TODO update to server IP
	$http({
   		url: "http://localhost:8080/recipe/" + recipeID.get(), 
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
 	});
});