package org.foo.web.model;

import org.foo.core.model.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public class AccountTransactions implements Serializable {

	private List<Transaction> creditingTransactions;
	private List<Transaction> debitingTransactions;

	public AccountTransactions(List<Transaction> creditingTransactions, List<Transaction> debitingTransactions) {
		this.creditingTransactions = creditingTransactions;
		this.debitingTransactions = debitingTransactions;
	}

	public List<Transaction> getCreditingTransactions() {
		return creditingTransactions;
	}

	public void setCreditingTransactions(List<Transaction> creditingTransactions) {
		this.creditingTransactions = creditingTransactions;
	}

	public List<Transaction> getDebitingTransactions() {
		return debitingTransactions;
	}

	public void setDebitingTransactions(List<Transaction> debitingTransactions) {
		this.debitingTransactions = debitingTransactions;
	}
}
