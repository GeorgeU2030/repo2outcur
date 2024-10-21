package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.PiLvlCategRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PiLvlCategServiceImpl implements PiLvlCategService {

    private final PiLvlCategRepository piLvlCategRepository;

}
