var editRecipe = angular.module('UserInfo');

var ingredients;
var init = true;

editRecipe.controller('EditAccount', function ($rootScope,$scope, $modal, $log, $http, $timeout,FileUploader) {

  $scope.image = '';
  $scope.userprofile = {};
  $scope.session = sessionStorage.whatever;
  $scope.userprofile = sessionStorage.profileEmail;
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
    });
   };
  $timeout($scope.init);

    var temp = {};
    temp.sessionID = sessionStorage.whatever;
    temp.image = $scope.image;
    $scope.sessionID = temp.sessionID;
    $scope.recipeID = temp.recipeID;

    var tempUploader = new FileUploader();
    tempUploader.url = "http://83.254.221.239:9000/editProfilePicture";

    tempUploader.onBeforeUploadItem = function (item)
    {
      item.alias = "image";
      item.formData.push(temp);
    };

    tempUploader.onSuccessItem = function(item, response, status, headers)
    {
      if (!response.success)
      {
        alert(response.error);
      }
      else
      {
        alert("Recipe Edited");
      }
    }
    $scope.uploader = tempUploader;

  $scope.UploadProfilePicture = function()
  {


    if($scope.uploader.queue.length === 0)
    {
      console.log("no image");
    }
    else
    {
      //With file
      $scope.uploader.uploadAll();        
    }
  };

  $scope.ChangePassword = function(newPassword)
  {
    $http({
        url: "http://83.254.221.239:9000/editPassword/",
        method: "GET",
        params: {sessionID: sessionStorage.whatever, password: newPassword},
    }).success(function(data) {
      $scope.email = data.email;
      $scope.country = data.country;
      $scope.username = data.username;
    });
    
  };

  $scope.ChangeUsername = function(newUsername)
  {
    $http({
        url: "http://83.254.221.239:9000/editUsername/",
        method: "GET",
        params: {sessionID: sessionStorage.whatever, username: newUsername},
    }).success(function(data) {
      if(data.success)
      {
        alert("username changed");
      }
    });
    
  };
});