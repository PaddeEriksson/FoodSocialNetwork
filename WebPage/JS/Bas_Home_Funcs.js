var BasicFunctions = angular.module('UserInfo');


//other homepage that we will include with ng-include :D
var templates =
[ { name: 'temp.html', url: 'post recipe.html'}];
var UserRcipes, Userinfo;

//Controle for all buttons on UserHomePage
BasicFunctions.controller('ButtonsControles', function($scope, $http) {

	$scope.URecipes_Collapse = false;

	$scope.template = templates[0];

	var temp = sessionStorage.whatever;
	
	$scope.logout = function(){
	//The Server request for logout	
		/*$http({
      	url: "http://83.254.221.239:9000/logout",
        method:"GET",
        params: temp
        }).success(function(data){

            if (!data.success)
            {
            	alert(data.error);
            }
            else
            {
            	alert("login successful");
            	 window.location='homepage.html';
            }
        });*/
    };

    $scope.GetUserInfo = function()
    {
    	temp.session = sessionStorage.whatever;
    		//The Server request for Geting User info	
		$http
		({
      	url: "http://83.254.221.239:9000/profile/{dkajhanidis@hotmail.com}",
        method:"GET",
        params: temp.session
        })
        .success(function(data)
        {

            if (!data.success)
            {
            	alert(data.error);
            }
            else
            {
            	alert("login successful");
            	console.log(data)
            	window.location='homepage.html';
            }
        });
    };

});