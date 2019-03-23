package org.foo.core.service;

import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionStatus;
import org.foo.core.repository.TransactionRepository;
import org.foo.core.service.authorization.AuthorizationService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public class TransactionServiceImplTest {
	private TransactionService transactionService;
	private AccountService accountService;
	private AuthorizationService authorizationService;
	private TransactionRepository transactionRepository;
	private Transaction transaction;

	@Before
	public void setUp() throws Exception {
		accountService = mock(AccountService.class);
		authorizationService = mock(AuthorizationService.class);
		transactionRepository = mock(TransactionRepository.class);

		transactionService = new TransactionServiceImpl(accountService,authorizationService,transactionRepository);
		transaction = new Transaction();
		transaction.setAmount(100);
		transaction.setDebitorAccountId("123");
		transaction.setCreditorAccountId("456");
	}

	@Test
	public void process_nominal() {
		// TODO
	}

	@Test
	public void process_not_authorized() {
		// TODO
	}

	@Test
	public void process_on_account_not_found() throws Exception{
		when(authorizationService.check(any(), anyDouble())).thenThrow(new AccountNotFoundException());
		try {
			TransactionStatus transactionStatus = transactionService.process(transaction);
			fail("An exception should have been thrown");
		} catch (AccountNotFoundException e){
			verify(authorizationService).check("123", 100);
			verify(accountService, never()).debit(any(), anyDouble());
			verify(accountService, never()).credit(any(), anyDouble());
		} catch (Exception e ){
			fail("Wrong exception sent");
		}
	}
}