package org.foo.core.service;

import org.foo.core.model.Transaction;
import org.foo.core.model.TransactionCategory;
import org.foo.core.model.stats.Distribution;
import org.foo.core.model.stats.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of {@link TransactionService}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Service
public class TransactionDistributionServiceImpl implements TransactionDistributionService {

	@Override
	public Map<TransactionCategory, Double> getDistributionMap(List<Transaction> transactions) {
		Map<TransactionCategory, Double> distributionMap = new LinkedHashMap<>();
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

	@Override
	public Distribution getDistribution(List<Transaction> transactions) {
		Map<TransactionCategory, Double> map = this.getDistributionMap(transactions);

		List<Point> points = new ArrayList<>(map.size());
		for (Map.Entry<TransactionCategory, Double> entry : map.entrySet()) {
			points.add(new Point(entry.getKey().name(), entry.getValue()));
		}

		return new Distribution(points);
	}
}
