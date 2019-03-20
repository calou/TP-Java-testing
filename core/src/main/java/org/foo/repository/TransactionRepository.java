package org.foo.repository;

import org.foo.model.Account;
import org.foo.model.Transaction;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for {@link Account}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
