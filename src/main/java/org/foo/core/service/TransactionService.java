package org.foo.core.service;

import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionStatus;

/**
 * Transaction service
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface TransactionService {

	TransactionStatus process(Transaction transaction) throws AccountNotFoundException;
}
