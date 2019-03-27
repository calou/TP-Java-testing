package org.foo.core.model;

/**
 * Status of an authorization
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public enum Authorization {
	AUTHORIZED(false), OVERDRAFT(false), NOT_ENOUGH_FUND(true),
	WEEKLY_TRESHOLD_REACHED(true), MONTHLY_TRESHOLD_REACHED(true), ACCOUNT_NOT_FOUND(true);

	private boolean blocking;

	Authorization(boolean blocking) {
		this.blocking = blocking;
	}

	public boolean isBlocking() {
		return blocking;
	}}
