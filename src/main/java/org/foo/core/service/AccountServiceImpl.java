package org.foo.core.service;

import org.foo.core.exception.AccountIdAlreadyExistsException;
import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Account;
import org.foo.core.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void createAccount(String accountId, double amount) throws AccountIdAlreadyExistsException {
		Optional<Account> existingAccount = accountRepository.findById(accountId);
		if(existingAccount.isPresent()){
			throw new AccountIdAlreadyExistsException();
		}
		Account account = new Account();
		account.setAccountId(accountId);
		account.setAmount(amount);
		account.setOverdraft(-200);
		account.setMaxOverdraft(-1000);
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

	public void credit(String accountNumber, double amount) throws AccountNotFoundException {
		Account account = findByAccountNumber(accountNumber);
		account.setAmount(account.getAmount() + amount);
		accountRepository.save(account);
	}

	public void debit(String accountNumber, double amount) throws AccountNotFoundException {
		Account account = findByAccountNumber(accountNumber);
		account.setAmount(account.getAmount() - amount);
		accountRepository.save(account);
	}
}
