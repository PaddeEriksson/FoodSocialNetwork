var UserInfo = angular.module('UserInfo',['ui.bootstrap']);
UserInfo.controller('Login',function($scope)
{
	$scope.setInfo = {Name:'User Name', PWord:'PassWord'};
	$scope.Submit_c = function()
	{
		//send info to server!!!
	};

});
UserInfo.controller('ModalDemoCtrl', function ($scope, $modal, $log) {

  $scope.items = ['Username:', 'Password:'];

  $scope.open = function (size) {

    var modalInstance = $modal.open({
      templateUrl: 'myModalContent.html',
      controller: 'ModalInstanceCtrl',
      size: size,
      resolve: {
        items: function () {
          return $scope.items;
        }
      }
    });

    modalInstance.result.then(function (selectedItem) {
      $scope.selected = selectedItem;
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };
});

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

UserInfo.controller('ModalInstanceCtrl', function ($scope, $modalInstance, items) {

  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  $scope.ok = function () {
    $modalInstance.close($scope.selected.item);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});