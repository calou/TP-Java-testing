package org.foo.core.service.authorization;

import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Account;
import org.foo.core.model.Authorization;
import org.foo.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that check whether a transaction if authorized
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	private AccountService accountService;
	private List<AuthorizationStrategy> strategies;

	@Autowired
	public AuthorizationServiceImpl(AccountService accountService, List<AuthorizationStrategy> strategies) {
		this.accountService = accountService;
		this.strategies = strategies;
	}

	@Override
	public Authorization check(String accountNumber, double amount) throws AccountNotFoundException {
		Account account = accountService.findByAccountNumber(accountNumber);
		for (AuthorizationStrategy strategy : strategies) {
			if (strategy.accept(account, amount)) {
				return strategy.getAuthorization();
			}
		}
		return null;
	}
}
