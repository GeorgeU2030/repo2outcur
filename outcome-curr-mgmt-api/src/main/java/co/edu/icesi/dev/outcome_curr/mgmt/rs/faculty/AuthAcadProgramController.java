package co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name ="AcademicProgramWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/acad_programs")
public interface AuthAcadProgramController {
    @GetMapping("/")
    public List<AcadProgramOutDTO> getAcadProgramsByFaculty(@PathVariable("facultyId") long facultyId) ;

    @GetMapping(value = "/{acadProgramId}")
    public AcadProgramOutDTO getAcadProgram(@PathVariable("facultyId") long facultyId,
            @PathVariable("acadProgramId") long acadProgramId) ;
}
