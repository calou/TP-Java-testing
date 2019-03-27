package org.foo.core.service.authorization;

import org.foo.core.model.Account;
import org.foo.core.model.Authorization;
import org.foo.core.repository.TransactionRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * {@link AuthorizationStrategy} that checks whether the account has reached its monthly threshold
 * on the current month
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Component
@Order(6)
public class MonthlyAmountReachedAuthorizationStrategy implements AuthorizationStrategy {

	private TransactionRepository transactionRepository;

	@Autowired
	public MonthlyAmountReachedAuthorizationStrategy(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public boolean accept(Account account, double amount) {
		Date sevenDaysAgo = DateTime.now().withDayOfMonth(0).withTime(0,0,0,0).toDate();
		Integer monthlySpent = transactionRepository.getSpentAmountFromDate(account.getAccountId(), sevenDaysAgo);
		return account.getWeeklyAuthorizedAmount() < monthlySpent;
	}

	@Override
	public Authorization getAuthorization() {
		return Authorization.MONTHLY_TRESHOLD_REACHED;
	}
}
