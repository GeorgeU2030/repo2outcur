package co.edu.icesi.dev.outcome_curr_mgmt.rs.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.AcadProgramInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AdminAcadProgramController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.AcadProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminAcadProgramControllerImpl implements AdminAcadProgramController {
    //TODO move to auth controller and remove, add test coverage
    private final AcadProgramService acadProgramService;

    @Override
    public ResponseEntity<AcadProgramOutDTO> createAcadProgram(long facultyId, AcadProgramInDTO acadProgramInDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(acadProgramService.createAcadProgram(facultyId, acadProgramInDTO));
    }

    @Override
    public ResponseEntity<Void> updateAcadProgram(long facultyId, long acadProgramId,
            AcadProgramInDTO acadProgramInDTO) {
        acadProgramService.updateAcadProgram(facultyId, acadProgramId, acadProgramInDTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteAcadProgram(long facultyId, long acadProgramId) {
        acadProgramService.deleteAcadProgram(facultyId, acadProgramId);
        return ResponseEntity.noContent().build();
    }
}
