package org.foo.repository;

import org.foo.model.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for {@link Account}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface AccountRepository extends CrudRepository<Account, String> {
}
