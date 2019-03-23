package org.foo.core.service.authorization;

import org.foo.core.model.Account;
import org.foo.core.model.Authorization;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * {@link AuthorizationStrategy} that accept transaction if the account has enough fund
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Component
@Order(3)
public class SolvableAuthorizationStrategy implements AuthorizationStrategy {

	@Override
	public boolean accept(Account account, double amount) {
		double updatedBalance = account.getBalance() - amount;
		return updatedBalance >= account.getOverdraft();
	}

	@Override
	public Authorization getAuthorization() {
		return Authorization.AUTHORIZED;
	}
}
