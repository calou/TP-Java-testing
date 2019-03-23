package org.foo.web.controller;

import org.foo.core.exception.AccountIdAlreadyExistsException;
import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Account;
import org.foo.core.repository.AccountRepository;
import org.foo.core.service.AccountService;
import org.foo.web.model.AccountCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private AccountRepository accountRepository;

	@Autowired
	public AccountController(AccountService accountService, AccountRepository accountRepository) {
		this.accountService = accountService;
		this.accountRepository = accountRepository;
	}


	@PutMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<Boolean> create(@RequestBody AccountCreationRequest acr) throws AccountIdAlreadyExistsException {
		accountService.createAccount(acr.getAccountId(), acr.getBalance());
		return ResponseEntity.ok(true);
	}

	@GetMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<Iterable<Account>> list() {
		return ResponseEntity.ok(accountRepository.findAll());
	}

	@GetMapping(value = "/{account_number}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Account> findByAccountId(@RequestParam("account_id") String accountNumber) throws AccountNotFoundException {
		Account account = accountService.findByAccountNumber(accountNumber);
		return ResponseEntity.ok(account);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public String handleAccountNotFound(){
		return "Account not found";
	}

	@ExceptionHandler(AccountIdAlreadyExistsException.class)
	public ResponseEntity<Boolean> handleIdAlreadExists(){
		return new ResponseEntity(false, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
