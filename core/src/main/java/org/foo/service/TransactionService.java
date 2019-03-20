package org.foo.service;

import org.foo.exception.AccountNotFoundException;
import org.foo.model.Transaction;
import org.foo.model.TransactionStatus;

/**
 * Transaction service
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface TransactionService {

	TransactionStatus process(Transaction transaction) throws AccountNotFoundException;
}
