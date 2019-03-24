package org.foo.web.service;

import org.foo.web.model.AccountTransactions;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface AccountTransactionsService {

	AccountTransactions findByAccountId(String accountId);
}
