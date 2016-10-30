package org.dcsc.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Deprecated
@Service
public class DcscUserService {
    @Autowired
    private DcscUserRepository userRepository;

    public DcscUser save(DcscUser user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Page<DcscUser> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<DcscUser> getUserById(long id) {
        return userRepository.findUserById(id);
    }
}
