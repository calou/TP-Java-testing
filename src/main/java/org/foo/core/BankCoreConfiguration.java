package org.foo.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application class
 *
 * @author <a href="mailto:sebastien.gruchet@digimind.com">Sébastien Gruchet</a>
 */
@Configuration
@ComponentScan("org.foo.core")
@EnableJpaRepositories(basePackages="org.foo.core.repository")
@EnableAutoConfiguration
public class BankCoreConfiguration {
}
