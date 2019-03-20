package org.foo.service;

import org.foo.exception.AccountNotFoundException;
import org.foo.model.Account;
import org.foo.repository.AccountRepository;
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

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
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
