var Post_recipe = angular.module('UserInfo');

Post_recipe.controller('PostRecipe', function ($scope, $modal, $log, $http) {

  $scope.tempName = 'Units';
  $scope.ingredientsInfo = {Ingredients:'', optional:'',amount:'', unit:''};
  $scope.recipeInfo = {title:'', Instruction:'', Time:''};
  $scope.unitsSgs = {
    kg:'Kg',
    mg:'Mg',
    tbsp:'tbsp',
    L:'litres',
    ml:'ml',
    tsp:'tsp'
  };

  $scope.status = {
    isopen: false
  };
  $scope.ChngBtnName = function(newName){
    $scope.tempname = newName;
  };
  $scope.toggled = function(open) {
    $log.log('Dropdown is now: ', open);
  };

  $scope.toggleDropdown = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.status.isopen = !$scope.status.isopen;
  };

  $scope.RecepieWO = function (size) {

    var AccountInstance = $modal.open({
      templateUrl: 'postrecipe.html',
      controller: 'Postrecipesmodal',
      size: size,
      resolve: {
        items: function () {
          return $scope.tempName;
        }
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

Post_recipe.controller('Postrecipesmodal', function ($scope, $modalInstance, items, $http) {


  $scope.tempName = items;

 
  console.log($scope);
  $scope.selected = {
    item: $scope.tempName[0]
  };

  $scope.ok = function () {
/*  	console.log($scope.items);
  	$http({
url: "http://83.254.221.239:9000/Post recipe",
method:"GET",
params: $scope.items
}).success(function(data){

      if (!data.success)
       {alert(data.error);
       
      }
      else
      {
       alert("login successful");
      }
  	});*/
  
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});
