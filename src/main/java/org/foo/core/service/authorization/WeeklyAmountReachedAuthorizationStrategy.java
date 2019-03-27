package org.foo.core.service.authorization;

import org.apache.commons.lang3.time.DateUtils;
import org.foo.core.model.Account;
import org.foo.core.model.Authorization;
import org.foo.core.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * {@link AuthorizationStrategy} that checks whether the account has reached its weekly threshold
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Component
@Order(5)
public class WeeklyAmountReachedAuthorizationStrategy implements AuthorizationStrategy {

	private TransactionRepository transactionRepository;

	@Autowired
	public WeeklyAmountReachedAuthorizationStrategy(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public boolean accept(Account account, double amount) {
		Date sevenDaysAgo = DateUtils.addDays(new Date(), -7);
		Integer weeklySpent = transactionRepository.getSpentAmountFromDate(account.getAccountId(), sevenDaysAgo);
		return account.getWeeklyAuthorizedAmount() < weeklySpent;
	}

	@Override
	public Authorization getAuthorization() {
		return Authorization.WEEKLY_TRESHOLD_REACHED;
	}
}
