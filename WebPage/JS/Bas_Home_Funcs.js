var BasicFunctions = angular.module('UserInfo');


//other homepage that we will include with ng-include :D
var templates =
[ { name: 'temp.html', url: 'post recipe.html'}];
var UserRcipes, Userinfo;

//Controle for all buttons on UserHomePage
BasicFunctions.controller('ButtonsControles', function($scope, $http) {

	$scope.URecipes_Collapse = false;
    $scope.AllUserRecipes={};
    $scope.AllRecipes={};
	$scope.template = templates[0];

    var mySession={sessionID: ""};
    mySession.sessionID = sessionStorage.whatever;
	$scope.logout = function(){
	//The Server request for logout
    console.log("http://83.254.221.239:9000/profile/"+ sessionStorage.email);
		$http({
      	url: "http://83.254.221.239:9000/logout",
        method:"GET",
        params:{sessionID:mySession.sessionID} 
        }).success(function(data){

            if (!data.success)
            {
            	alert(data.error);
            }
            else
            {
                alert("Logout Success");
                window.location='homepage.html';
            }
        });
    };

    $scope.GetUserInfo = function()
    {
    	//The Server request for Geting User info
        /*console.log(sessionStorage.whatever);
		$http
		({
      	url: "http://83.254.221.239:9000/profile/"+ $scope.setInfo.email,
        method:"GET",
        params: {sessionID:mySession.sessionID} 
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
                //Store UserInfo
            }
        });*/
    };
    $scope.GetAllMyRecipes=function(isOpen){
        $scope.URecipes_Collapse = isOpen;
        //The Server request for Geting All user specific recipes
        $http
        ({
        url: "http://83.254.221.239:9000/showMyRecipes",
        method:"GET",
        params: {sessionID:mySession.sessionID} 
        })
        .success(function(data)
        {
            if (!data.success)
            {
                alert(data.error);
            }
            else
            {
                console.log(data)
                //Store MyRecipes
                AllUserRecipes=data.recipe;
                console.log(AllUserRecipes);
            }
        });
    };
    $scope.GetAllMyFavorites=function(isOpen){
        $scope.URecipes_Collapse = isOpen;
        //The Server request for Geting all favorites
        /*$http
        ({
        url: "http://83.254.221.239:9000/showMyRecipes",
        method:"GET",
        params: {sessionID:mySession.sessionID} 
        })
        .success(function(data)
        {
            if (!data.success)
            {
                alert(data.error);
            }
            else
            {
                console.log(data)
                //Store MyRecipes
                AllUserRecipes=data.recipes;
                console.log(AllUserRecipes);
            }
        });*/
    };
    $scope.GetAllRecipes=function(isOpen){
        $scope.URecipes_Collapse = isOpen;
        //The Server request for Geting All Recipes
        $http
        ({
        url: "http://83.254.221.239:9000/ShowAll",
        method:"GET",
        params: {sessionID:mySession.sessionID} 
        })
        .success(function(data)
        {
            if (!data.success)
            {
                alert(data.error);
            }
            else
            {
                console.log(data)
                //Store MyRecipes
                $scope.AllRecipes = data.recipes;
                console.log($scope.AllRecipes);
            }
        });
    };
});
