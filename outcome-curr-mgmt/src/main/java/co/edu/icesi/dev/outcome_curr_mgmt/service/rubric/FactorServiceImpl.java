package co.edu.icesi.dev.outcome_curr_mgmt.service.rubric;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FactorServiceImpl implements FactorService {

    private final FacultyRepository facultyRepository;

}
