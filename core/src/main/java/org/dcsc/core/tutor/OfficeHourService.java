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

    public OfficeHour getOfficeHour(long id) {
        return officeHourRepository.findOne(id);
    }

    public List<OfficeHour> getOfficeHours(Tutor tutor, AcademicTerm academicTerm) {
        return officeHourRepository.findByTutorAndAcademicTerm(tutor, academicTerm);
    }

    public OfficeHour save(OfficeHour officeHour) {
        return officeHourRepository.save(officeHour);
    }

    public List<OfficeHour> save(Collection<OfficeHour> officeHours) {
        return officeHourRepository.save(officeHours);
    }

    /**
     * Cannot delete by instance. Tutors can be holding onto the reference.
     *
     * @param id
     * @param deleteChildren
     */
    public void delete(long id, boolean deleteChildren) {
        if (deleteChildren) {
            for (OfficeHour officeHour = getOfficeHour(id); officeHour != null; officeHour = officeHour.getChildOfficeHour()) {
                officeHourRepository.delete(officeHour.getId());
            }
        } else {
            officeHourRepository.delete(id);
        }
    }
}
