package co.edu.icesi.dev.outcome_curr_mgmt.academic_registry.client;

import co.edu.icesi.dev.outcome_curr.mgmt.model.banner.data.BannerCourseDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BannerAPI {
    List<FacultyInDTO> getFacultiesList();
    Page<FacultyInDTO> getFacultiesPage(int page, int size);
    List<FacultyInDTO> importFaculties(List<String> facultiesNames);

    List<AcadProgramOutDTO> getAcadProgramsList(String facultyName);
    Page<AcadProgramOutDTO> getAcadProgramsPage(int page, int size, String facultyName);
    List<AcadProgramOutDTO> importAcadPrograms(String facultyName, List<String> acadProgramsNames);
}
