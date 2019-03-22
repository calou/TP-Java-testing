package org.foo.core.service;

import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionCategory;

import java.util.List;
import java.util.Map;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface TransactionDistributionService {

	Map<TransactionCategory, Double> getDistribution(List<Transaction> transactions);
}
