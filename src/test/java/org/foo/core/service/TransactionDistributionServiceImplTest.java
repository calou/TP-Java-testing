package org.foo.core.service;

import com.google.common.collect.Lists;
import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionCategory;
import org.foo.core.model.stats.Distribution;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TransactionDistributionServiceImpl}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public class TransactionDistributionServiceImplTest {
	private TransactionDistributionServiceImpl service;
	private List<Transaction> transactions;

	@Before
	public void setUp() throws Exception {
		service = new TransactionDistributionServiceImpl();

		transactions = Lists.newArrayList(
				createDummyTransaction(TransactionCategory.MORTGAGE, 200),
				createDummyTransaction(TransactionCategory.INTERESTS, 350),
				createDummyTransaction(TransactionCategory.MORTGAGE, 10),
				createDummyTransaction(TransactionCategory.SALARY, 99),
				createDummyTransaction(TransactionCategory.INTERESTS, 30)
		);
	}

	@Test
	public void getDistributionMap_nominal() {
		Map<TransactionCategory, Double> distributionMap = service.getDistributionMap(transactions);
		assertThat(distributionMap).hasSize(3)
				.containsEntry(TransactionCategory.MORTGAGE, 210d);

		// FIXME à compléter
	}

	@Test
	public void getDistribution_nominal() {
		Distribution distribution = service.getDistribution(transactions);
		assertThat(distribution.getPoints()).hasSize(3)
				.extracting("name")
				.containsExactly("MORTGAGE", "INTERESTS", "SALARY");

		// FIXME tester les montants pour chaque point
	}

	private Transaction createDummyTransaction(TransactionCategory category, int amount) {
		return new Transaction("", "", null, category, amount);
	}
}