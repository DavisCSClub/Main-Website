package org.dcsc.athena.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.dcsc.athena.objects.TutoringSession;
import org.dcsc.athena.objects.TutoringSessionRepository;
import java.util.List;


@Service
public class TutoringSessionService {

	@Autowired
    private TutoringSessionRepository tutoringSessionRepository;

    public void save(TutoringSession s) {
    	tutoringSessionRepository.save(s);
    }

    public TutoringSession findTutoringSessionById(Long id) {
    	return tutoringSessionRepository.findTutoringSessionById(id);
    }

    public List<TutoringSession> findOpenTutoringSessionByTutorId(Long id) {
    	return tutoringSessionRepository.findOpenTutoringSessionByTutorId(id);
    }

    

}
