var Post_recipe = angular.module('UserInfo');
var ingredients;
Post_recipe.controller('PostRecipe', function ($scope, $modal, $log, $http) {

  $scope.tempName = 'Units';
  $scope.ingredientsInfo = {name:'', isOptional: false, amount:'', amountType:''};
  $scope.recipeInfo = {title:'', instruction:'BLA BLA BLA', time: 0};
  $scope.UinitsPr={
    Kg:'Kg',
    Mg:'Mg',
    tbsp:'tbsp',
    liters:'liters',
    ml:'ml',
    tsp:'tsp'
  };

  ingredients = $scope.ingredient=[];

  $scope.status = {
    isopen: false
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
    //console.log(ingredients);
    console.log('GJ');
  };

  $scope.RecepieWO = function (size) {

    var AccountInstance = $modal.open({
      templateUrl: 'postrecipe.html',
      controller: 'Postrecipesmodal',
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
    });
  }; 

});
// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

Post_recipe.controller('Postrecipesmodal', function ($scope, $modalInstance, RecepieN, $http) {
  $scope.recipeInfo=RecepieN;
  var temp = $scope.recipeInfo;
  temp.session = sessionStorage.whatever;
  
  //console.log(temp);

  $scope.ok = function () {
  temp.ingredients = JSON.stringify(ingredients);;
 	console.log($scope.items);
  console.log(temp.ingredients);
  	$http({
      url: "http://83.254.221.239:9000/createRecipe",
        method:"GET",
        params: temp
        }).success(function(data){

            if (!data.success)
             {alert(data.error);
             
            }
            else
            {
             alert("login successful");
            }
        	});
          console.log(RecepieN);
          console.log(ingredients[0]);
        
        };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});
