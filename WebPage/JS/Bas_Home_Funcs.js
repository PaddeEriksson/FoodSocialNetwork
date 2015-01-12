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
var templates2 = 
[ { name: 'temp2.html', url: 'Edit recipe page.html'}];
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

    $scope.searchString = "";
	$scope.URecipes_Collapse = true;
    $scope.ListRecipes = true;
    $scope.SearchRecipesList = true;
    $scope.AllUserRecipes={};
    $scope.AllFavoriteRecipes={};
    $scope.AllSearchRecipes={};
    $scope.AllRecipes={};
	$scope.template = templates[0];
    $scope.template2 = templates2[0];
    $scope.AddFriendReq ="dk@hotmail.com";
    $scope.DeleteFriendReq ="dk@hotmail.com";
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
        var location = "EditAccountLayout.html"
        sessionStorage.profileEmail = sessionStorage.email;
        window.location= location;

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
    $scope.GetAllMyRecipes=function(){
        //The Server request for Geting All user specific recipes
        $scope.URecipes_Collapse = true;
        $scope.ListRecipes = true;
        $scope.SearchRecipesList = true;


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
                $scope.URecipes_Collapse = false;
            }
        });
        

    };
    $scope.SearchRecipes=function(isOpen,searchStrings){
        //The Server request for Geting All user specific recipes
        $scope.SearchRecipesList = true;
        $scope.URecipes_Collapse = true;
        $scope.ListRecipes = true;
        console.log($scope.searchString);
        $http
        ({
        url: "http://83.254.221.239:9000/searchRecipe",
        method:"GET",
        params: {sessionID:mySession.sessionID,searchString:$scope.searchString} 
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
                $scope.AllSearchRecipes=data.recipes;
                console.log($scope.AllSearchRecipes);
                $scope.SearchRecipesList = false;
            }
        });

    };
    $scope.GetAllMyFavorites=function(isOpen){
        $scope.ListRecipes = true;
        $scope.URecipes_Collapse = true;
        $scope.SearchRecipesList = true;
        $http
        ({
        url: "http://83.254.221.239:9000/searchRecipe",
        method:"GET",
        params: {sessionID:mySession.sessionID,favorites:true,searchString:""} 
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
                $scope.AllFavoriteRecipes=data.recipes;
                console.log($scope.AllUserRecipes);
                $scope.ListRecipes = false;
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
    $scope.DeleteRecipe=function(recID)
    {
        $http
        ({
        url: "http://83.254.221.239:9000/deleteRecipe",
        method:"GET",
        params: {sessionID:mySession.sessionID,recipeID:recID} 
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
                alert("Recipe Deleted");
                $scope.GetAllMyRecipes(false);
            }
        });
    };

    $scope.EditRecipe=function(recID)
    {
        var location = "Edit recipe page.html";
        sessionStorage.recipeID = recID;
        window.location = location;
    };

    $scope.RemoveFromFavorite=function(recipeIDIN)
    {
        $http
        ({
            url: "http://83.254.221.239:9000/deleteFavorite",
            mathod:"GET",
            params: {sessionID:mySession.sessionID,recipeID:recipeIDIN}
        }).success(function(data)
        {
            if(!data.success)
            {
                alert(data.error);
            }
            else
            {
                alert("Removed from favorite");
                $scope.GetAllMyFavorites(false);
            }
        });
    };

    $scope.GetAllRecipes=function(isOpen)
    {
        $scope.URecipes_Collapse = isOpen;
        //The Server request for Geting All Recipes
        $http
        ({
        url: "http://83.254.221.239:9000/showAll",
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

    $scope.AddFriend=function()
    {
        
        //The Server request for Geting All Recipes
        $http
        ({
        url: "http://83.254.221.239:9000/addFriend",
        method:"GET",
        params: {sessionID:mySession.sessionID, follower:$scope.AddFriendReq} 
        })
        .success(function(data)
        {
            if (!data.success)
            {
                alert(data.error);
            }
            else
            {
                /*console.log(data)
                //Store MyRecipes
                $scope.AllMyFriends = data.recipes;
                console.log($scope.AllRecipes);*/
            }
        });
    };
    $scope.RemoveFriend=function(){
        //The Server request for Geting All Recipes
        $http
        ({
        url: "http://83.254.221.239:9000/deleteFriend",
        method:"GET",
        params: {sessionID:mySession.sessionID, follower:$scope.AddFriendReq} 
        })
        .success(function(data)
        {
            if (!data.success)
            {
                alert(data.error);
            }
            else
            {
                /*console.log(data)
                //Store MyRecipes
                $scope.AllRecipes = data.recipes;
                console.log($scope.AllRecipes);*/
           }
        });
    };
});
