package org.dcsc.core.authentication.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("newGroupService")
public class GroupService {
    private static final int UNIVERSAL_GROUP_ID = 1;

    @Autowired
    private GroupRepository repository;

    @Transactional(readOnly = true)
    public Group getDefault() {
        return getById(UNIVERSAL_GROUP_ID);
    }

    @Transactional(readOnly = true)
    public Group getById(int id) {
        return repository.findOne(id);
    }
}
