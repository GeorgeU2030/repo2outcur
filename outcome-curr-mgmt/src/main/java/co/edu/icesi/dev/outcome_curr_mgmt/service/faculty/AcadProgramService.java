package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.AcadProgramInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;

import java.util.List;

public interface AcadProgramService {

    List<AcadProgram> getAcadProgramsByFaculty(long facultyId);

    AcadProgramOutDTO getAcadProgram(long facultyId, long acadProgramId);

    AcadProgramOutDTO createAcadProgram(long facultyId, AcadProgramInDTO acadProgramtInDTO);

    void updateAcadProgram(long facultyId, long acadProgramId, AcadProgramInDTO acadProgramtInDTO);

    void deleteAcadProgram(long facultyId, long acadProgramId);

}
