package co.edu.icesi.dev.outcome_curr_mgmt.rs.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthAcadProgramController;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.AcadProgramMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.AcadProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthAcadProgramControllerImpl implements AuthAcadProgramController {
    //TODO add test coverage
    private final AcadProgramService acadProgramService;
    private final AcadProgramMapper acadProgramMapper;



    @Override
    public List<AcadProgramOutDTO> getAcadProgramsByFaculty(long facultyId) {
        return acadProgramMapper.acadProgramsToAcadProgramOutDtos(
                acadProgramService.getAcadProgramsByFaculty(facultyId));
    }

    @Override
    public AcadProgramOutDTO getAcadProgram(long facultyId, long acadProgramId) {
        return acadProgramService.getAcadProgram(facultyId, acadProgramId);
    }

}
