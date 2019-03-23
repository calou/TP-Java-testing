package org.foo.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("account_id")
	private String accountId;

	@Column(name = "balance")
	private double balance;

	@Column(name = "overdraft")
	private double overdraft;

	@Column(name = "max_overdraft")
	@JsonProperty("max_overdraft")
	private double maxOverdraft;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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
