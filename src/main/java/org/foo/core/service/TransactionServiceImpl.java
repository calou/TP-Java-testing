package org.foo.core.service;

import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Authorization;
import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionStatus;
import org.foo.core.repository.TransactionRepository;
import org.foo.core.service.authorization.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Service that process transaction
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Service
public class TransactionServiceImpl implements TransactionService {
	private AccountService accountService;
	private AuthorizationService authorizationService;
	private TransactionRepository transactionRepository;

	@Autowired
	public TransactionServiceImpl(AccountService accountService, AuthorizationService authorizationService, TransactionRepository transactionRepository) {
		this.accountService = accountService;
		this.authorizationService = authorizationService;
		this.transactionRepository = transactionRepository;
	}

	@Override
	@Transactional
	public TransactionStatus process(Transaction transaction) throws AccountNotFoundException {
		String debitorId = transaction.getDebitorAccountId();
		double amount = transaction.getAmount();
		Authorization authorization = authorizationService.check(debitorId, amount);
		TransactionStatus transactionStatus = new TransactionStatus();
		transactionStatus.setAuthorization(authorization);
		if(!authorization.isBlocking()){
			accountService.debit(debitorId, amount);
			accountService.credit(transaction.getCreditorAccountId(), amount);
			transaction.setDate(new Date());
			transactionRepository.save(transaction);
		}
		return transactionStatus;
	}
}
