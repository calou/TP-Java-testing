package org.foo.web.controller;

import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionCategory;
import org.foo.core.service.TransactionService;
import org.foo.web.model.AccountTransactions;
import org.foo.web.service.AccountTransactionsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for {@link TransactionController}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("integration-test")
@AutoConfigureMockMvc
@DirtiesContext
@EnableTransactionManagement
public class TransactionControllerTest {

	@MockBean
	private TransactionService transactionService;

	@MockBean
	private AccountTransactionsService accountTransactionsService;

	@Autowired
	private TransactionController controller;

	@Autowired
	private MockMvc mvc;

	@Test
	public void findByAccountId_nominal() {
		AccountTransactions accountTransactions = mock(AccountTransactions.class);
		when(accountTransactionsService.findByAccountId(anyString())).thenReturn(accountTransactions);

		ResponseEntity<AccountTransactions> responseEntity = controller.findByAccountId("azerty");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isEqualTo(accountTransactions);
	}

	@Test
	public void findByAccountId_integration() throws Exception {
		AccountTransactions accountTransactions = new AccountTransactions(
				Arrays.asList(createTransaction("a", "b", 12), createTransaction("c","d", 34)),
				Arrays.asList(createTransaction("e", "d", 56), createTransaction("f","g", 78))
		);

		when(accountTransactionsService.findByAccountId(anyString())).thenReturn(accountTransactions);

		mvc.perform(get("/transaction/{account_id}", "azerty")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.crediting[0].creditor", equalTo("a")))
				.andExpect(jsonPath("$.debiting[1].debitor", equalTo("g")));

	}

	private Transaction createTransaction(String cred, String deb, double amount) {
		return new Transaction(cred, deb, new Date(), TransactionCategory.MORTGAGE, amount);
	}
}