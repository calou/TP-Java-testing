package org.foo.web.controller;

import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionStatus;
import org.foo.core.repository.TransactionRepository;
import org.foo.core.service.TransactionService;
import org.foo.web.model.AccountTransactions;
import org.foo.web.service.AccountTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@RestController
@RequestMapping("transaction")
public class TransactionController {
	private TransactionService transactionService;
	private AccountTransactionsService accountTransactionsService;

	@Autowired
	public TransactionController(TransactionService transactionService, AccountTransactionsService accountTransactionsService) {
		this.transactionService = transactionService;
		this.accountTransactionsService = accountTransactionsService;
	}

	@PutMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<TransactionStatus> create(@RequestBody Transaction transaction) throws AccountNotFoundException {
		TransactionStatus transactionStatus = transactionService.process(transaction);
		switch (transactionStatus.getAuthorization()) {
			case NOT_ENOUGH_FUND:
				return new ResponseEntity<>(transactionStatus, HttpStatus.UNPROCESSABLE_ENTITY);
			case OVERDRAFT:
			case AUTHORIZED:
				return ResponseEntity.ok(transactionStatus);
			default:
				return new ResponseEntity<>(transactionStatus, HttpStatus.NOT_ACCEPTABLE);

		}
	}

	@GetMapping(value = "/{account_id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<AccountTransactions> findByAccountId(@PathVariable("account_id") String accountId) {
		return ResponseEntity.ok(accountTransactionsService.findByAccountId(accountId));
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public String handleAccountNotFound() {
		return "Account not found";
	}

}
