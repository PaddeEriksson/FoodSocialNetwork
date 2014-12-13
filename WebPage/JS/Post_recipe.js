var Post_recipe = angular.module('UserInfo');

Post_recipe.controller('PostRecipe', function ($scope, $modal, $log, $http) {


  $scope.items = {title:'', Instruction:'', Time:'', Ingredients:'', optional:'',amount:'', unit:'', add: '' };

  $scope.RecepieWO = function (size) {

    var AccountInstance = $modal.open({
      templateUrl: 'postrecipe.html',
      controller: 'Postrecipesmodal',
      size: size,
      resolve: {
        items: function () {
          return $scope.items;
        }
      }
    });

    AccountInstance.result.then(function (selectedItem) {
      $scope.selected = selectedItem;
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };
});
// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

Post_recipe.controller('Postrecipesmodal', function ($scope, $modalInstance, items, $http) {

  $scope.items = items;
 
  console.log($scope);
  $scope.selected = {
    item: $scope.items[0]
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
