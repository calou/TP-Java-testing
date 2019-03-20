package org.foo.web.controllers;

import org.foo.exception.AccountNotFoundException;
import org.foo.model.Account;
import org.foo.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@RestController
public class AccountController {
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/#{account_number}")
	public ResponseEntity<Account> findByAccountNumber(@RequestParam("account_number") String accountNumber) throws AccountNotFoundException {
		Account account = accountService.findByAccountNumber(accountNumber);
		return ResponseEntity.ok(account);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public String handleAccountNotFound(){
		return "Account not found";
	}
}
