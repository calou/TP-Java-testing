package org.foo.core.repository;

import org.foo.core.model.Account;
import org.foo.core.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository for {@link Account}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

	List<Transaction> findAllByCreditorAccountId(String accountId);
	List<Transaction> findAllByDebitorAccountId(String accountId);
}
