package org.foo.core.service.authorization;

import org.foo.core.exception.AccountNotFoundException;
import org.foo.core.model.Authorization;

/**
 * Transaction authorization service
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface AuthorizationService {

	Authorization check(String accountNumber, double amount) throws AccountNotFoundException;

}
