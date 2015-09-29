package org.dcsc.core.model.carousel;

import javassist.NotFoundException;

/**
 * Created by tktong on 8/8/2015.
 */
public class EntityIdNotFoundException extends NotFoundException {
    private long id;

    public EntityIdNotFoundException(long id, String message) {
        super(message);

        setId(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
