package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;

import java.util.List;

public interface FacultyService {
    FacultyOutDTO createFaculty(FacultyInDTO facultyInDTO);
    FacultyOutDTO getFacultyByFacId(long facId);
    FacultyOutDTO getFacultyByFacNameInSpa(String name);
    FacultyOutDTO getFacultyByFacNameInEng(String name);
    List<FacultyOutDTO> getFaculties();
    FacultyOutDTO updateFaculty(long facultyId, FacultyInDTO facultyInDTO);
    void deleteFaculty(long facId);
}