package org.dcsc.security.user.form;

import org.dcsc.security.user.DcscUser;

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
