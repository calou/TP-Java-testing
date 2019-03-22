package org.foo.core.service;

import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionCategory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Service
public class TransactionDistributionServiceImpl implements TransactionDistributionService {
	@Override
	public Map<TransactionCategory, Double> getDistribution(List<Transaction> transactions) {
		Map<TransactionCategory, Double> distributionMap = new HashMap<>();
		transactions.stream().forEach(t -> {
			TransactionCategory category = t.getCategory();
			if(!distributionMap.containsKey(category)){
				distributionMap.put(category, t.getAmount());
			} else {
				distributionMap.put(category, distributionMap.get(category) + t.getAmount());
			}
		});
		return distributionMap;
	}
}
