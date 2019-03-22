package org.foo.core.service;

import org.foo.core.model.Authorization;

/**
 * Transaction authorization service
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface AuthorizationService {

	Authorization check(String accountNumber, double amount);

}
