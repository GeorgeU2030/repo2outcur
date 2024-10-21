package co.edu.icesi.dev.outcome_curr_mgmt.rs.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthFacultyController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthFacultyControllerImpl implements AuthFacultyController {

    private final FacultyService facultyService;

    @Override
    public List<FacultyOutDTO> getFaculties() {
        return facultyService.getFaculties();
    }

    @Override
    public FacultyOutDTO getFacultyByFacId(long facultyId) {
        return facultyService.getFacultyByFacId(facultyId);
    }

    @Override
    public FacultyOutDTO getFacultyByFacNameInSpa(String name) {
        return facultyService.getFacultyByFacNameInSpa(name);
    }

    @Override
    public FacultyOutDTO getFacultyByFacNameInEng(String name) {
        return facultyService.getFacultyByFacNameInEng(name);
    }

    @Override
    public FacultyOutDTO updateFaculty(long facultyId, FacultyInDTO facultyInDTO) {
        return facultyService.updateFaculty(facultyId, facultyInDTO);
    }

    @Override
    public FacultyOutDTO createFaculty(FacultyInDTO facultyInDTO) {
        return facultyService.createFaculty(facultyInDTO);
    }

    @Override
    public ResponseEntity<Void> deleteFaculty(long facultyId){
        facultyService.deleteFaculty(facultyId);
        return ResponseEntity.noContent().build();
    }

}
