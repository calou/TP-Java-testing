package org.foo.core.service;

import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionCategory;
import org.foo.core.model.stats.Distribution;

import java.util.List;
import java.util.Map;

/**
 * Returns the distribution of amount per transaction category
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface TransactionDistributionService {

	Map<TransactionCategory, Double> getDistributionMap(List<Transaction> transactions);

	Distribution getDistribution(List<Transaction> transactions);
}
