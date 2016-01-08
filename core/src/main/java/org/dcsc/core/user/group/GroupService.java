package org.dcsc.core.user.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    // TODO: Use a more appropriate exception
    @Transactional(readOnly = true)
    public Group getGroup(String groupName) throws Exception {
        return groupRepository.findGroupByName(groupName)
                .orElseThrow(() -> new Exception(String.format("Could not find group with name - %s", groupName)));
    }
}
