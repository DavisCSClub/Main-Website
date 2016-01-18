package org.dcsc.core.tutor;

import org.dcsc.core.time.AcademicTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeHourService {
    @Autowired
    private OfficeHourRepository officeHourRepository;

    @Transactional
    public OfficeHour getOfficeHour(long id) {
        return officeHourRepository.findOne(id);
    }

    @Transactional
    public List<OfficeHour> getOfficeHours(Tutor tutor, AcademicTerm academicTerm) {
        return officeHourRepository.findByTutorAndAcademicTerm(tutor, academicTerm);
    }

    @Transactional
    public OfficeHour save(OfficeHour officeHour) {
        return officeHourRepository.save(officeHour);
    }

    @Transactional
    public List<OfficeHour> save(Collection<OfficeHour> officeHours) {
        return officeHourRepository.save(officeHours);
    }

    public void delete(long id, boolean deleteChildren) {
        delete(getOfficeHour(id), deleteChildren);
    }

    @Transactional
    public void delete(OfficeHour officeHour, boolean deleteChildren) {
        Optional<OfficeHour> parent = officeHourRepository.findParentOfficeHour(officeHour.getId());

        // Free the parent's reference to the child office hour
        if (parent.isPresent()) {
            OfficeHour parentOfficeHour = parent.get();
            parentOfficeHour.setChildOfficeHour(null);

            officeHourRepository.save(parentOfficeHour);
        }

        if (deleteChildren) {
            ArrayList<OfficeHour> officeHours = new ArrayList<>();
            for (OfficeHour oh = officeHour; oh != null; oh = oh.getChildOfficeHour()) {
                officeHours.add(oh);
            }

            officeHourRepository.delete(officeHours);
        } else {
            officeHourRepository.delete(officeHour);
        }
    }
}
