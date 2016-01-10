package org.dcsc.core.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
public class AcademicTermService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcademicTermService.class);

    @Autowired
    private AcademicTermRepository academicTermRepository;
    @Autowired
    private AcademicTermComparator academicTermComparator;

    public AcademicTerm getCurrentTerm() throws Exception {
        AcademicTerm academicTerm;
        LocalDateTime today = LocalDateTime.now();
        int currentYear = today.getYear();

        List<AcademicTerm> academicTerms = academicTermRepository.findByYear(currentYear);

        if (academicTerms.isEmpty()) {
            LOGGER.error("Could not find any academic terms for calendar year " + currentYear);

            // Going to try and recover
            String currentQuarter;
            int currentMonth = today.getMonthValue();

            if ((Month.SEPTEMBER.getValue() <= currentMonth) && (currentMonth <= Month.DECEMBER.getValue())) {
                currentQuarter = AcademicTermNames.FALL_QUARTER;
            } else if ((Month.JANUARY.getValue() <= currentMonth) && (currentMonth <= Month.MARCH.getValue())) {
                if ((currentMonth == Month.MARCH.getValue()) && (today.getDayOfMonth() > 24)) {
                    currentQuarter = AcademicTermNames.SPRING_QUARTER;
                } else {
                    currentQuarter = AcademicTermNames.WINTER_QUARTER;
                }
            } else if ((Month.APRIL.getValue() <= currentMonth) && (currentMonth <= Month.JUNE.getValue())) {
                currentQuarter = AcademicTermNames.SPRING_QUARTER;
            } else {
                currentQuarter = AcademicTermNames.SUMMER_SESSION_1;
            }

            academicTerm = new AcademicTerm(0, currentQuarter, currentYear);
            save(academicTerm);
        } else {
            academicTerms.sort(academicTermComparator);
            academicTerm = academicTerms.get(academicTerms.size() - 1);
        }

        return academicTerm;
    }

    private void save(AcademicTerm academicTerm) {
        academicTermRepository.save(academicTerm);
    }

}
