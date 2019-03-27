package org.foo.core.service;

import org.foo.core.exception.AccountIdAlreadyExistsException;
import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Account;
import org.foo.core.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Default implementation of {@link AccountService}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Service
public class AccountServiceImpl implements AccountService {
	private AccountRepository accountRepository;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public void createAccount(String accountId, double amount) throws AccountIdAlreadyExistsException {
		Optional<Account> existingAccount = accountRepository.findById(accountId);
		if(existingAccount.isPresent()){
			throw new AccountIdAlreadyExistsException();
		}
		Account account = new Account();
		account.setAccountId(accountId);
		account.setBalance(amount);
		account.setOverdraft(-200);
		account.setMaxOverdraft(-1000);
		account.setWeeklyAuthorizedAmount(500);
		account.setMonthlyAuthorizedAmount(2000);
		accountRepository.save(account);
	}

	public Account findByAccountNumber(String accountNumber) throws AccountNotFoundException {
		Optional<Account> optional = accountRepository.findById(accountNumber);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new AccountNotFoundException();
		}
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void credit(String accountNumber, double amount) throws AccountNotFoundException {
		Account account = findByAccountNumber(accountNumber);
		account.setBalance(account.getBalance() + amount);
		accountRepository.save(account);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void debit(String accountNumber, double amount) throws AccountNotFoundException {
		Account account = findByAccountNumber(accountNumber);
		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);
	}
}
