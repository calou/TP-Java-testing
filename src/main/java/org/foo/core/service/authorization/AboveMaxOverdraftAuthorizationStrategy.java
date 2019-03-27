package org.foo.core.service.authorization;

import org.foo.core.model.Account;
import org.foo.core.model.Authorization;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * {@link AuthorizationStrategy} that accept transaction that would set the account balance
 * above its max overdraft value
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Component
@Order(5)
public class AboveMaxOverdraftAuthorizationStrategy implements AuthorizationStrategy {

	@Override
	public boolean accept(Account account, double amount) {
		double updatedBalance = account.getBalance() - amount;
		return  updatedBalance < account.getMaxOverdraft();
	}

	@Override
	public Authorization getAuthorization() {
		return Authorization.NOT_ENOUGH_FUND;
	}
}
