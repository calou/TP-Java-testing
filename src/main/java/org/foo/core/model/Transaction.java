package org.foo.core.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Transaction between two accounts
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Entity
@Table(name="transactions")
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
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="category")
	private TransactionCategory category;

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

	public TransactionCategory getCategory() {
		return category;
	}

	public void setCategory(TransactionCategory category) {
		this.category = category;
	}
}
