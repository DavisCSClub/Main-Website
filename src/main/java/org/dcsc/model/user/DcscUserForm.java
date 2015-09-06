package org.dcsc.model.user;

import org.dcsc.model.user.DcscUser;

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
