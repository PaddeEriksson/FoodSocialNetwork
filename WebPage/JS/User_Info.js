var UserInfo = angular.module('UserInfo',[]);
UserInfo.controller('Login',function($scope)
{
	$scope.setInfo = {Name:'Name', PWord:'PassWord'};
	$scope.Submit_c = function()
	{
		//send ifo to server!!!
	};

})