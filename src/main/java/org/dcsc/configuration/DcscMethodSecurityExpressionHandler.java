package org.dcsc.configuration;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class DcscMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
    private static final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    @Autowired
    private DcscPermissionEvaluator permissionEvaluator;

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        DcscMethodSecurityExpressionOperation operation = new DcscMethodSecurityExpressionOperation(authentication);
        operation.setThis(invocation.getThis());
        operation.setPermissionEvaluator(permissionEvaluator);
        operation.setTrustResolver(trustResolver);
        operation.setRoleHierarchy(getRoleHierarchy());
        return operation;
    }
}
