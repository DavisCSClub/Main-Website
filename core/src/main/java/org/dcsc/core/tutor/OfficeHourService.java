package org.dcsc.core.tutor;

import org.dcsc.core.time.AcademicTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class OfficeHourService {
    @Autowired
    private OfficeHourRepository officeHourRepository;

    public List<OfficeHour> getOfficeHours(Tutor tutor, AcademicTerm academicTerm) {
        return officeHourRepository.findByTutorAndAcademicTerm(tutor, academicTerm);
    }

    public OfficeHour save(OfficeHour officeHour) {
        return officeHourRepository.save(officeHour);
    }

    public List<OfficeHour> save(Collection<OfficeHour> officeHours) {
        return officeHourRepository.save(officeHours);
    }
}
