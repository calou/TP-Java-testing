package org.foo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Transaction between two accounts
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "creditor_account_id")
	private String creditorAccountId;

	@Column(name = "debitor_account_id")
	private String debitorAccountId;

	@Column(name = "amount")
	private double amount;

	@Column(name="date")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreditorAccountId() {
		return creditorAccountId;
	}

	public void setCreditorAccountId(String creditorAccountId) {
		this.creditorAccountId = creditorAccountId;
	}

	public String getDebitorAccountId() {
		return debitorAccountId;
	}

	public void setDebitorAccountId(String debitorAccountId) {
		this.debitorAccountId = debitorAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
