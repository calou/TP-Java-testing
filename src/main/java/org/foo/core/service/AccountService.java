package org.foo.core.service;

import org.foo.core.exception.AccountIdAlreadyExistsException;
import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Account;

/**
 * Service for managing {@link Account}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface AccountService {

	void createAccount(String accountNumber, double amount) throws AccountIdAlreadyExistsException;

	Account findByAccountNumber(String accountNumber) throws AccountNotFoundException;

	void credit(String accountNumber, double amount) throws AccountNotFoundException;

	void debit(String accountNumber, double amount) throws AccountNotFoundException;
}
