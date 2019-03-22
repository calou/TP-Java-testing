package org.foo.core.repository;

import org.foo.core.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository for {@link Account}
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, String> {
}
