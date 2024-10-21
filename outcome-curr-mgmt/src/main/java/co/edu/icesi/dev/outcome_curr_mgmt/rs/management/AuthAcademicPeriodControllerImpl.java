package co.edu.icesi.dev.outcome_curr_mgmt.rs.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.AcadPeriodInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.AcadPeriodOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.management.AuthAcademicPeriodController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.management.AcPeriodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class AuthAcademicPeriodControllerImpl implements AuthAcademicPeriodController {
    //TODO add test coverage
    private final AcPeriodService academicPeriodService;

    @Override
    public AcadPeriodOutDTO addAcademicPeriod(AcadPeriodInDTO academicPeriodToCreate) {
        return academicPeriodService.addAcademicPeriod(academicPeriodToCreate);
    }

    @Override
    public AcadPeriodOutDTO getAcademicPeriod(Long acadPeriodId) {

        return academicPeriodService.searchAcademicPeriod(acadPeriodId);
    }

    @Override
    public List<AcadPeriodOutDTO> getAllAcademicPeriods() {

        return academicPeriodService.getAllAcademicPeriods();
    }

    @Override
    public AcadPeriodOutDTO setAcademicPeriod(Long acadPeriodId, AcadPeriodInDTO acadPeriodInDTO) {
        return academicPeriodService.setAcademicPeriod(acadPeriodId, acadPeriodInDTO);
    }

    @Override
    public ResponseEntity<Void> deleteAcademicPeriod(Long acadPeriodId) {
        academicPeriodService.deleteAcademicPeriod(acadPeriodId);
        return ResponseEntity.noContent().build();
    }
}
