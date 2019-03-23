var bankApp = angular.module('bankApp', ['ngResource']);

bankApp.factory('Account', ['$resource',
    function($resource) {
      return $resource('/account', {}, {
        save: {method: 'PUT'},
        query: {
          method: 'GET',
          isArray: true
        }
      });
    }
]);
bankApp.controller('AccountController', function AccountController($scope, Account) {
  $scope.accounts = Account.query();
  $scope.createAccountRequest = {
    "account_id": "",
    "amount": 0
  }
  $scope.create = function(account_id, amount) {
    var account = new Account();
    account.account_id = account_id;
    account.amount = amount;
    savePromise = account.$save(function(account, putResponseHeaders) {
        $scope.accounts = Account.query();
    });
  }
});