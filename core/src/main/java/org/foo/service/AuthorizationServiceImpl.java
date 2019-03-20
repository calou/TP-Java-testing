package org.foo.service;

import org.foo.exception.AccountNotFoundException;
import org.foo.model.Account;
import org.foo.model.Authorization;
import org.springframework.stereotype.Service;

/**
 * Service that check whether a transaction if authorized
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	private AccountService accountService;

	public AuthorizationServiceImpl(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public Authorization check(String accountNumber, double amount) {
		try {
			Account account = accountService.findByAccountNumber(accountNumber);
			if (account.getAmount() > account.getOverdraft() + amount) {
				return Authorization.AUTHORIZED;
			} else {
				return Authorization.NOT_ENOUGH_FUND;
			}
		} catch (AccountNotFoundException e) {
			return Authorization.ACCOUNT_NOT_FOUND;
		}
	}
}
