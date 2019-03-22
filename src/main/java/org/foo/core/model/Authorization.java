package org.foo.core.model;

/**
 * Status of an authorization
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public enum Authorization {
	AUTHORIZED, NOT_ENOUGH_FUND, WEEKLY_TRESHOLD_REACHED, MONTHLY_TRESHOLD_REACHED, ACCOUNT_NOT_FOUND;
}
