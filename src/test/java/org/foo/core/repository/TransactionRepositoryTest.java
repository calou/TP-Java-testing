package org.foo.core.repository;

import org.apache.commons.lang3.time.DateUtils;
import org.foo.core.BankCoreConfiguration;
import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TransactionRepository}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankCoreConfiguration.class})
@ActiveProfiles("test")
@DirtiesContext
public class TransactionRepositoryTest {

	@Autowired
	private TransactionRepository transactionRepository;

	private Date now;

	@Before
	public void setUp() throws Exception {
		now = new Date();
	}

	@Test
	public void getSpentAmountFromDate_nominal() throws Exception {

		storeTransaction(now, "a", 100);
		storeTransaction(DateUtils.addHours(now, -30), "a", 50);
		storeTransaction(DateUtils.addDays(now, -6), "a", 300);
		storeTransaction(DateUtils.addDays(now, -10), "a", 600);

		assertThat(transactionRepository.getSpentAmountFromDate("a", DateUtils.addDays(now, -2))).isEqualTo(150);
		assertThat(transactionRepository.getSpentAmountFromDate("a", DateUtils.addDays(now, -7))).isEqualTo(450);
	}

	private void storeTransaction(Date date, String accountId, int amount) {
		transactionRepository.save(new Transaction("b", accountId, date, TransactionCategory.SALARY, amount));
	}
}