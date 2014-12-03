var UserInfo = angular.module('UserInfo',[]);
UserInfo.controller('Login',function($scope)
{
	$scope.setInfo = {Name:'User Name', PWord:'PassWord'};
	$scope.Submit_c = function()
	{
		//send info to server!!!
	};

})