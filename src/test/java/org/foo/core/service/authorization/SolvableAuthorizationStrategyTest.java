package org.foo.core.service.authorization;

import org.foo.core.model.Account;
import org.foo.core.model.Authorization;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Tests for {@link SolvableAuthorizationStrategy}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public class SolvableAuthorizationStrategyTest {
	private SolvableAuthorizationStrategy strategy;
	private Account account;

	@Before
	public void setUp() throws Exception {
		strategy = new SolvableAuthorizationStrategy();
		account = new Account();
		account.setBalance(10);
		account.setOverdraft(-5);
	}

	@Test
	public void accept_nominal() {
		assertThat(strategy.accept(account, 10)).isTrue();
		assertThat(strategy.accept(account, 15)).isTrue();

		assertThat(strategy.accept(account, 16)).isFalse();
	}

	@Test
	public void getAuthorization_nominal() {
		assertThat(strategy.getAuthorization()).isEqualTo(Authorization.AUTHORIZED);
	}
}