package org.dcsc.core.officer;

import org.dcsc.core.officer.DcscOfficer;
import org.dcsc.core.officer.DcscOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tktong on 8/3/2015.
 */
@Service
public class DcscOfficerService {
    @Autowired
    private DcscOfficerRepository dcscOfficerRepository;

    public List<DcscOfficer> getOfficers(int year) {
        return dcscOfficerRepository.findDcscOfficersByYear(year);
    }

    public List<Integer> getDistinctYears() {
        return dcscOfficerRepository.findDistinctYears();
    }
}
