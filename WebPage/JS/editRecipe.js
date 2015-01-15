var editRecipe = angular.module('UserInfo');

var ingredients;
var init = true;

editRecipe.controller('EditRecipe', function ($rootScope, $scope, $modal, $log, $http) {

  $scope.tempName = 'Units';
  $scope.ingredientsInfo = {name:'', isOptional: false, amount:'', amountType:''};
  $scope.recipeInfo = {title:'', instruction:'', time: 0, image: ''};
  $scope.UinitsPr={
    Kg:'Kg',
    Mg:'Mg',
    g:'g',
    tbsp:'tbsp',
    liters:'liters',
    ml:'ml',
    dl:'dl',
    tsp:'tsp'
  };

  ingredients = $scope.ingredient=[];

  $scope.status = {
    isopen: false
  };

  $scope.removeING = function(index) {
    ingredients.splice(index, 1);
  };

  $scope.ChngBtnName = function(newName){
    $scope.tempName = newName;
    $scope.ingredientsInfo.amountType = newName;
  };
  $scope.toggled = function(open) {
    $log.log('Dropdown is now: ', open);
  };

  $scope.toggleDropdown = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.status.isopen = !$scope.status.isopen;
  };

  $scope.SveIng = function(lol){
    var temp = { name: lol.name, amount:lol.amount, amountType:lol.amountType, isOptional:lol.isOptional};


    ingredients.push(temp);
    console.log(ingredients);
    console.log('GJ');
  };

  $rootScope.$on('SveIng2', function(event, lol)
  {
    if(init)
    {
      for(i = 0; i < lol.length; i++)
      {
        $scope.SveIng(lol[i]);   
      }
    }
    init = false;

  });

  $scope.RecepieWO = function (size) {

    var AccountInstance = $modal.open({
      templateUrl: 'editrecipe.html',
      controller: 'Editrecipesmodal',
      size: size,
      resolve: {
        RecepieN: function () {
          return $scope.recipeInfo;
        },
        RecepieIng: function () {
          return ingredients;
        },
      }
    });

    AccountInstance.result.then(function (selectedItem) {
      $scope.selected = selectedItem;
    },

    function () {
      $log.info('Modal dismissed at: ' + new Date());
      init=true;
    });
  }; 

});



editRecipe.controller('Editrecipesmodal', function ($rootScope, $scope, $modalInstance, RecepieN, $http, FileUploader) {
  //prepare a Temporare Variable to combine information in the same object
  //in order to send it all in the same request
  $scope.recipeInfo=RecepieN;
  var temp = $scope.recipeInfo;
  temp.sessionID = sessionStorage.whatever;
  temp.recipeID = sessionStorage.recipeID;
  temp.recipeTitle = "tempTitle";

  $scope.sessionID = temp.sessionID;
  $scope.recipeID = temp.recipeID;

  setTimeout(function()
  {
  $http({
    url: "http://83.254.221.239:9000/recipe/" + sessionStorage.recipeID, 
    method: "GET",
    params: {sessionID: temp.sessionID}
  }).success(function(data)
  {
    if(!data.success)
    {
      alert(data.error);
    }
    else
    {
      console.log(data);
      $scope.recipeInfo.title = data.recipe.recipeTitle;
      $scope.recipeInfo.instruction = data.recipe.instruction;
      $scope.recipeInfo.time = data.recipe.time;
      $rootScope.$emit('SveIng2',data.ingridients);

    }
  });
  }, 100);
  var tempUploader = new FileUploader();
  tempUploader.url = "http://83.254.221.239:9000/editRecipe";

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
  $scope.ok = function () {

    temp.ingredients = JSON.stringify(ingredients);
    console.log(temp);
    if($scope.uploader.queue.length === 0)
    {
      //No file
      $http({
        url: "http://83.254.221.239:9000/editRecipe",
        method:"GET",
        params: temp 
        }).success(function(data){

          if (!data.success)
          {
            alert(data.error);
          }
          else
          {
           alert("Recipe Edited");
          }
        });    
    }
    else
    {
      //With file
      $scope.uploader.uploadAll();        
    }
  };
  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});