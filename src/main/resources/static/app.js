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
bankApp.factory('Transaction', ['$resource',
    function($resource) {
      return $resource('/transaction', {}, {
        save: {method: 'PUT'},
        query: {
          method: 'GET',
          isArray: true
        }
      });
    }
]);
bankApp.controller('AccountController', function AccountController($scope, Account, Transaction) {
  $scope.accounts = Account.query();
  $scope.createAccountRequest = {};
  $scope.createTransactionRequest = {};
  $scope.createAccount = function(account_id, balance) {
    var account = new Account();
    account.account_id = account_id;
    account.balance = balance;
    account.$save(function(account, putResponseHeaders) {
        $scope.accounts = Account.query();
        $scope.createAccountRequest = {};
    });
  }

  $scope.createTransaction = function(debitor, creditor, category, amount) {
      var transaction = new Transaction();
      transaction.debitor = debitor;
      transaction.creditor = creditor;
      transaction.category = category;
      transaction.amount = amount;
      transaction.$save(function(transaction, putResponseHeaders) {
          $scope.accounts = Account.query();
          $scope.createTransactionRequest = {};
      });
    }
});