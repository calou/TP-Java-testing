package org.foo.core.service.authorization;

import org.foo.core.model.Account;
import org.foo.core.model.Authorization;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * {@link AuthorizationStrategy} that accept transaction that would set the account balance
 * between its overdraft and max overdraft value
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Component
@Order(1)
public class OverdraftAuthorizationStrategy implements AuthorizationStrategy {

	@Override
	public boolean accept(Account account, double amount) {
		double updatedBalance = account.getAmount() - amount;

		return updatedBalance < account.getOverdraft() && updatedBalance > account.getMaxOverdraft();
	}

	@Override
	public Authorization getAuthorization() {
		return Authorization.OVERDRAFT;
	}
}
