package com.wincore.saasgestiondestock.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;


// ce class actice automatiquement le filter hibernate  avant chaque appel aux repositories
// pour chaque appel aux repo HIBERNATE ajoute automatiquement WHERE tenant_id = tenatId

@Aspect
@Component
public class TenantHibernateFilter {


    @PersistenceContext
    private EntityManager entityManager;

    @Before("execution(* com.wincore.saasgestiondestock.repositories.*Repository.*(..))")
    public void activateTenantFilter() {
        final String tenantId = TenantContext.getCurrentTenant();

        if (tenantId !=null) {
            final Session session = this.entityManager.unwrap(Session.class);

            // activer le filter et injecter le parameter tenantId
            session.enableFilter("tenantFilter").setParameter("tenantId",tenantId);
        }
    }
}
