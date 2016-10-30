package org.dcsc.core.user.group;

import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class UserGroupService {
    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private GroupService groupService;
    @Autowired
    private DcscUserService dcscUserService;

    public void removeUserGroup(DcscUser dcscUser, Group group) {
        dcscUser.removeGroup(group.getName());
        userGroupRepository.deleteByDcscUserAndGroup(dcscUser, group);
        dcscUserService.save(dcscUser);
    }

    public void removeUserGroup(DcscUser dcscUser, String groupName) throws Exception {
        removeUserGroup(dcscUser, groupService.getGroup(groupName));
    }
}