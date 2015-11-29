package org.dcsc.core.user;

/**
 * Created by tktong on 8/5/2015.
 */
public interface DcscUserForm {
    /**
     *
     * @return
     */
    DcscUser build();


    /**
     *
     */
    DcscUser build(DcscUser dcscUser);
}
