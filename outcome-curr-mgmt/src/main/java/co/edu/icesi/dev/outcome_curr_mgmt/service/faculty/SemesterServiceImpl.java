package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService {

    private final SemesterRepository semesterRepository;

}
