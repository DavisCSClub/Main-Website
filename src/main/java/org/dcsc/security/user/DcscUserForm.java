package org.dcsc.security.user;

/**
 * Created by tktong on 7/26/2015.
 */
public class DcscUserForm {
    private long id = -1;
    private String username;
    private String email;
    private String name;
    private String password;
    private String confirmPassword;

    public DcscUserForm() {
    }


    public DcscUserForm(DcscUser dcscUser) {
        this.id = dcscUser.getId();
        this.username = dcscUser.getUsername();
        this.email = dcscUser.getEmail();
        this.name = dcscUser.getName();
        this.password = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
