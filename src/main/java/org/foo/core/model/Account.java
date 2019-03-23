package org.foo.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Bank account
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Entity
@Table(name="accounts")
public class Account implements Serializable {

	@Id
	private String accountId;

	@Column(name = "amount")
	private double amount;

	@Column(name = "overdraft")
	private double overdraft;

	@Column(name = "max_overdraft")
	private double maxOverdraft;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	public double getMaxOverdraft() {
		return maxOverdraft;
	}

	public void setMaxOverdraft(double maxOverdraft) {
		this.maxOverdraft = maxOverdraft;
	}
}
