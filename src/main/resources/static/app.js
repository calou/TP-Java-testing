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
    "balance": 0
  }
  $scope.create = function(account_id, balance) {
    var account = new Account();
    account.account_id = account_id;
    account.balance = balance;
    savePromise = account.$save(function(account, putResponseHeaders) {
        $scope.accounts = Account.query();
    });
  }
});