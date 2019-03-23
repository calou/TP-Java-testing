package org.foo.core.service.authorization;

import org.foo.core.model.Account;
import org.foo.core.model.Authorization;

/**
 * Strategy for getting the authorization for a transaction
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface AuthorizationStrategy {

	boolean accept(Account account, double amount);

	Authorization getAuthorization();
}
