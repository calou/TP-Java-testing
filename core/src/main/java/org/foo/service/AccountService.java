package org.foo.service;

import org.foo.exception.AccountNotFoundException;
import org.foo.model.Account;

/**
 * Service for managing {@link Account}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface AccountService {

	Account findByAccountNumber(String accountNumber) throws AccountNotFoundException;

	void credit(String accountNumber, double amount) throws AccountNotFoundException;

	void debit(String accountNumber, double amount) throws AccountNotFoundException;
}
