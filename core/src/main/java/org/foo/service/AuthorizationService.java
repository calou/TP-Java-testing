package org.foo.service;

import org.foo.model.Authorization;

/**
 * Transaction authorization service
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface AuthorizationService {

	Authorization check(String accountNumber, double amount);

}
