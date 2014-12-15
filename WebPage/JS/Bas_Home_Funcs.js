var BasicFunctions = angular.module('UserInfo');

BasicFunctions.controller('CollapseField', function ($scope) {
	$scope.Home_Collapse = true;
	$scope.URecipes_Collapse = true;
	$scope.Buttons_Names = ['Logout','My Recipes', 'Home' ];
});

