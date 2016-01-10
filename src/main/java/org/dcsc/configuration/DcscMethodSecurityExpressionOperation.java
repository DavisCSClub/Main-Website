package org.dcsc.configuration;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;


public class DcscMethodSecurityExpressionOperation extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private Object filterObject;
    private Object returnObject;
    private Object target;
    private DcscPermissionEvaluator permissionEvaluator;

    public DcscMethodSecurityExpressionOperation(Authentication authentication) {
        super(authentication);
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public void setPermissionEvaluator(PermissionEvaluator permissionEvaluator) {
        super.setPermissionEvaluator(permissionEvaluator);
        this.permissionEvaluator = (DcscPermissionEvaluator) permissionEvaluator;
    }

    public void setThis(Object target) {
        this.target = target;
    }

    public boolean hasPermission(String category, String permission) {
        return permissionEvaluator.hasPermission(authentication, category, permission);
    }

    public boolean hasGroup(String group) {
        return hasGroup(group, false);

    }

    public boolean hasGroup(String group, boolean adminOnly) {
        return permissionEvaluator.hasGroup(authentication, group, true);
    }
}
