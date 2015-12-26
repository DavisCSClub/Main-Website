package org.dcsc.admin.dto;

public class RestTransactionResult {
    public static RestTransactionResult success(String message) {
        return new RestTransactionResult(true, message);
    }

    public static RestTransactionResult fail(String message) {
        return new RestTransactionResult(false, message);
    }

    private boolean success;
    private String message;

    public RestTransactionResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
