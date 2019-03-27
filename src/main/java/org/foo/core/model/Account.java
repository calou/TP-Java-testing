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

	@Column(name = "weekly_authorized_amount")
	@JsonProperty("weekly_authorized_amount")
	private int weeklyAuthorizedAmount;

	@Column(name = "monthly_authorized_amount")
	@JsonProperty("monthly_authorized_amount")
	private int monthlyAuthorizedAmount;

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

	public int getWeeklyAuthorizedAmount() {
		return weeklyAuthorizedAmount;
	}

	public void setWeeklyAuthorizedAmount(int weeklyAuthorizedAmount) {
		this.weeklyAuthorizedAmount = weeklyAuthorizedAmount;
	}

	public int getMonthlyAuthorizedAmount() {
		return monthlyAuthorizedAmount;
	}

	public void setMonthlyAuthorizedAmount(int monthlyAuthorizedAmount) {
		this.monthlyAuthorizedAmount = monthlyAuthorizedAmount;
	}
}
