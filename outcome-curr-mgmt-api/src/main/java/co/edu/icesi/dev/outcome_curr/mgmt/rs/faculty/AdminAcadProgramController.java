package co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.AcadProgramInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/acad_programs")
public interface AdminAcadProgramController {
    //TODO move to auth controller and remove
    @PostMapping
    ResponseEntity<AcadProgramOutDTO> createAcadProgram(@PathVariable("facultyId") long facultyId,
            @Valid @RequestBody AcadProgramInDTO acadProgramInDTO);

    @PatchMapping(value = "/{acadProgramId}")
    ResponseEntity<Void> updateAcadProgram(@PathVariable("facultyId") long facultyId,
            @PathVariable("acadProgramId") long acadProgramId, @RequestBody AcadProgramInDTO acadProgramInDTO);

    @DeleteMapping(value = "/{acadProgramId}")
    ResponseEntity<Void> deleteAcadProgram(@PathVariable("facultyId") long facultyId,
            @PathVariable("acadProgramId") long acadProgramId);
}
