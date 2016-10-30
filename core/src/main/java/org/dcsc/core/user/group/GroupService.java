package org.dcsc.core.user.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Deprecated
@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Transactional(readOnly = true)
    public Group getGroup(String groupName) throws Exception {
        return groupRepository.get(groupName);
    }
}
