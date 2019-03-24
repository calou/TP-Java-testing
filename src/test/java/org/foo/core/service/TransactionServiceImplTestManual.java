package org.foo.core.service;

import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Account;
import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Manual test for {@link TransactionServiceImpl}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
@DirtiesContext
public class TransactionServiceImplTestManual {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

	@Test
	public void process_nominal() throws Exception {

		accountService.createAccount("first", 200);
		accountService.createAccount("second", 200);

		Transaction transaction = new Transaction("first", "second", null, TransactionCategory.MORTGAGE, 50);
		transactionService.process(transaction);

		Account firstAccount = accountService.findByAccountNumber("first");
		Account secondAccount = accountService.findByAccountNumber("second");
		assertThat(firstAccount.getBalance()).isEqualTo(250);
		assertThat(secondAccount.getBalance()).isEqualTo(150);
	}

	@Test
	public void process_unknown_creditor() throws Exception {
		accountService.createAccount("third", 200);
		Transaction transaction = new Transaction("fourth", "third", null, TransactionCategory.MORTGAGE, 50);
		try {
			transactionService.process(transaction);
		} catch (AccountNotFoundException e) {
			// Expected
		}
		Account thirdAccount = accountService.findByAccountNumber("third");
		assertThat(thirdAccount.getBalance()).isEqualTo(200);
	}
}