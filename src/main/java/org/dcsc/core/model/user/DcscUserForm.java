package org.dcsc.core.model.user;

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
