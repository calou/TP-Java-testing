package org.foo.core.repository;

import org.foo.core.model.Account;
import org.foo.core.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository for {@link Account}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
	List<Transaction> findAllByCreditorAccountId(String accountId);
	List<Transaction> findAllByDebitorAccountId(String accountId);

	@Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.debitorAccountId=:id AND t.date>:d")
	Integer getSpentAmountFromDate(@Param("id") String accountId, @Param("d") Date date);
}
