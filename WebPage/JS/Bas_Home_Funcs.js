var BasicFunctions = angular.module('UserInfo');


BasicFunctions.factory('userProfileID', function()
{
    var savedData = {};
    function set(data)
    {
        savedData = data;
    }
    function get()
    {
        return savedData;
    }

    return {
        set: set,
        get: get
    }
});

//other homepage that we will include with ng-include :D
var templates =
[ { name: 'temp.html', url: 'post recipe.html'}];
var UserRcipes, Userinfo;

BasicFunctions.controller('testController', function($scope, $http) {
    $http({
        url: "http://83.254.221.239:9000/profile/"+ sessionStorage.email,
        method: "GET",
        params:{sessionID:sessionStorage.whatever}
    }).success(function(data)
    {
        if(!data.success)
        {

        }
        else
        {
            $scope.userName = data.username;
        }
    });
});

//Controle for all buttons on UserHomePage
BasicFunctions.controller('ButtonsControles', function($scope, $http) {

	$scope.URecipes_Collapse = true;
    $scope.AllUserRecipes={};
    $scope.AllRecipes={};
	$scope.template = templates[0];
    console.log("http://83.254.221.239:9000/profile/"+ sessionStorage.email);

    var mySession={sessionID: ""};
    mySession.sessionID = sessionStorage.whatever;
    $scope.session = sessionStorage.whatever;
	$scope.logout = function(){
	//The Server request for logout
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
        //The Server request for Geting All user specific recipes
        $scope.URecipes_Collapse = isOpen;

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
                //Store MyRecipes
                $scope.AllUserRecipes=data.recipes;
                console.log($scope.AllUserRecipes);
            }
        });

    };
    $scope.GetAllMyFavorites=function(isOpen){
        $scope.URecipes_Collapse = isOpen;

        $http
        ({
        url: "http://83.254.221.239:9000/searchRecipe",
        method:"GET",
        params: {sessionID:mySession.sessionID,favorites:true,SerachString:""} 
        })
        .success(function(data)
        {
            if (!data.success)
            {
                alert(data.error);
            }
            else
            {
                //Store MyRecipes
                $scope.AllUserRecipes=data.recipes;
                console.log($scope.AllUserRecipes);
            }
        });
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
    $scope.ShowSingleRecipe=function(recID)
    {
        var location = "showRecipeLayout.html"
        sessionStorage.recipeID = recID;
        window.location= location;
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
