package org.dcsc.core.user;


import org.springframework.security.core.AuthenticationException;

public class NoLinkedAccountException extends AuthenticationException {
    public NoLinkedAccountException(String msg) {
        super(msg);
    }
}
