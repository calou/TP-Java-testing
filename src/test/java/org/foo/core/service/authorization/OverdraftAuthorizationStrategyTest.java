package org.foo.core.service.authorization;

import org.foo.core.model.Account;
import org.foo.core.model.Authorization;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Todo
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public class OverdraftAuthorizationStrategyTest {
	private OverdraftAuthorizationStrategy strategy;
	private Account account;

	@Before
	public void setUp() throws Exception {
		strategy = new OverdraftAuthorizationStrategy();
		account = new Account();
		account.setBalance(10);
		account.setOverdraft(-5);
		account.setMaxOverdraft(-10);
	}

	@Test
	public void accept_nominal() {
		assertTrue(strategy.accept(account, 16));
		// FIXME à compléter
	}

	@Test
	public void getAuthorization_nominal() {
		assertThat(strategy.getAuthorization()).isEqualTo(Authorization.OVERDRAFT);
	}
}