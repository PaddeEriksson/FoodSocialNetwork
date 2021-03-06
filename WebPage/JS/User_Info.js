var UserInfo = angular.module('UserInfo',['ui.bootstrap', 'angularFileUpload']);



UserInfo.controller('SetUserInfo', function ($scope, $modal, $log, $http) {

  $scope.setInfo = {email:'', password:''};

  $scope.open = function (size) {

    var modalInstance = $modal.open({
      templateUrl: 'myModalContent.html',
      controller: 'OpenUserInfo',
      size: size,
      resolve: {
        items: function () {
          return $scope.setInfo;
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

UserInfo.controller('OpenUserInfo', function ($scope, $modalInstance, items, $http, $location) {

  $scope.myPathVariable = 'user home page.html';

  $scope.setInfo = items;
  $scope.selected = {
    item: $scope.setInfo[0]
  };


  $scope.login = function () { 
    $http({
      url: "http://83.254.221.239:9000/login",
      method:"GET",
      params: $scope.setInfo
    }).success(function(data){
      if (!data.success)
       {alert(data.error);
       
      }
      else
      {
        sessionStorage.whatever=data.sessionID;
        sessionStorage.email=$scope.setInfo.email;
        window.location= $scope.myPathVariable;
      }
    });

    $modalInstance.close($scope.selected.item);
  };


  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});