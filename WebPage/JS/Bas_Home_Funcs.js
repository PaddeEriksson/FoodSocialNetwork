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
[ { name: 'temp.html', url: 'post recipe.html'},
{ name: 'temp2.html', url: 'Edit recipe page.html'}];
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
    $scope.MyFriends_Collapse=true;
    $scope.SearchFriendList = true;

    $scope.AllUserRecipes={};
    $scope.AllFavoriteRecipes={};
    $scope.AllSearchRecipes={};
    $scope.AllRecipes={};
	$scope.template = templates[0];

    $scope.AllMyFriends={};
    $scope.SearchAFriend="";
    $scope.AllUsersSearch={};

    $scope.template2 = templates[1];
    $scope.AddFriendReq ="";
    $scope.DeleteFriendReq ="";


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
    };

    $scope.GoToFriends = function(){
        var location = "EditAccountLayout.html"
        sessionStorage.profileEmail = sessionStorage.email;
        window.location= location;
    };

    $scope.GetAllMyRecipes=function(){
        //The Server request for Geting All user specific recipes
        $scope.URecipes_Collapse = true;
        $scope.ListRecipes = true;
        $scope.SearchRecipesList = true;
        $scope.MyFriends_Collapse = true;

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
        $scope.MyFriends_Collapse = true;
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
        $scope.MyFriends_Collapse = true;
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


    $scope.MyFriends=function(isOpen){
        $scope.MyFriends_Collapse = isOpen;
        $scope.ListRecipes = true;
        $scope.URecipes_Collapse = true;
        $scope.SearchRecipesList = true;
        //The Server request for Geting All Recipes
        $http
        ({
        url: "http://83.254.221.239:9000/showMyFriends",
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
                $scope.AllMyFriends = data.friends;
                console.log($scope.AllMyFriends[0].email);
                $scope.MyFriends_Collapse = false;
            }
        });
    };


     $scope.SearchFriends=function(isOpen)
     {
        //The Server request for Geting All user specific recipes
        $scope.SearchRecipesList = true;
        $scope.URecipes_Collapse = true;
        $scope.ListRecipes = true;
        $scope.SearchFriendList = true;
        console.log($scope.SearchAFriend);
        $http
        ({
        url: "http://83.254.221.239:9000/searchUser",
        method:"GET",
        params: {sessionID:mySession.sessionID, searchString:$scope.SearchAFriend} 
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
                $scope.AllUsersSearch=data.friends;
                console.log($scope.AllUsersSearch);
                $scope.SearchFriendList = false;
            }

        });
    };


    $scope.AddFriend=function(usetinfo)
    {        
       //The Server request for Geting All Recipes
        $http
        ({
        url: "http://83.254.221.239:9000/addFriend",
        method:"GET",
        params: {sessionID:mySession.sessionID, follower:usetinfo} 
        })
        .success(function(data)
        {
            if (!data.success)
            {
                alert(data.error);
            }
            else
            {
                alert("You just added a firnd");
                $scope.MyFriends($scope.MyFriends_Collapse);
            }
        });
    };

    $scope.RemoveFriend=function(usetinfo){
        //The Server request for Geting All Recipes
        $http
        ({
        url: "http://83.254.221.239:9000/deleteFriend",
        method:"GET",
        params: {sessionID:mySession.sessionID, follower:usetinfo} 
        })
        .success(function(data)
        {
            if (!data.success)
            {
                alert(data.error);
            }
            else
            {
                alert("You stop following "+ usetinfo);
                $scope.MyFriends($scope.MyFriends_Collapse);
            }
        });
    };

});
