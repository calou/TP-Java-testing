package org.foo.web.controllers;

import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Account;
import org.foo.core.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@RestController
@RequestMapping("account")
public class AccountController {
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/{account_number}")
	@ResponseBody
	public ResponseEntity<Account> findByAccountNumber(@RequestParam("account_number") String accountNumber) throws AccountNotFoundException {
		Account account = accountService.findByAccountNumber(accountNumber);
		return ResponseEntity.ok(account);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public String handleAccountNotFound(){
		return "Account not found";
	}
}
