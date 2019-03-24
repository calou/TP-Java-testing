package org.foo.web.service;

import org.foo.core.model.Transaction;
import org.foo.core.repository.TransactionRepository;
import org.foo.web.model.AccountTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Service
public class CachedAccountTransactionsService implements AccountTransactionsService {
	private TransactionRepository transactionRepository;

	@Autowired
	public CachedAccountTransactionsService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	@Cacheable("account_transactions")
	public AccountTransactions findByAccountId(String accountId) {
		List<Transaction> creditingTransactions = transactionRepository.findAllByCreditorAccountId(accountId);
		List<Transaction> debitingTransactions = transactionRepository.findAllByDebitorAccountId(accountId);
		return new AccountTransactions(creditingTransactions, debitingTransactions);
	}
}
