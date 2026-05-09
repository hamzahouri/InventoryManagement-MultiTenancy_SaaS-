package com.wincore.saasgestiondestock.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;

// ce filter va intercepter chaque requete HTTP pour identifier le tenant,
// c'est le point d'entrer du mecanisme multi-tenant
// il s'execute avant tous les controller

@Component
//pour s'executer des la premier avant tous les controllers
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantFilter implements Filter {

    private static final String TENANT_HEADER = "X-Tenant-ID";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String tenantId = resolveTenant(request);

        if (tenantId == null || tenantId.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
           // filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        try {
            // stocker le tenant dans le thread local
            TenantContext.setCurrentTenant(tenantId);
            filterChain.doFilter(servletRequest, servletResponse);

        } finally {
            // CRITIQUE : pour nettoyer le thread et eviter le tenant de fuiter ver la requette suivante
            TenantContext.clear();

        }
       // filterChain.doFilter(servletRequest, servletResponse);
    }


    private String resolveTenant (final HttpServletRequest request) {
        final String tenantId = request.getHeader(TENANT_HEADER);

        if (tenantId !=null && !tenantId.isBlank()) {
     return tenantId.trim().toLowerCase();
        }

        return null;
    }
}
