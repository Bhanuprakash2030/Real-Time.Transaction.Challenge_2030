package dev.codescreen.cs.configurations;

import dev.codescreen.cs.data.Account;
import dev.codescreen.cs.data.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.serialization.Serializer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = Account.class, basePackages = {"org.axonframework.eventhandling.tokenstore.jpa"})
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
public class ApplicationConfigurations {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public TokenStore tokenStore(Serializer serializer, EntityManagerProvider entityManagerProvider, TransactionManager transactionManager) {
        return JpaTokenStore.builder()
                .serializer(serializer)
                .entityManagerProvider(entityManagerProvider)
                .build();
    }

    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return () -> entityManager;
    }
}
