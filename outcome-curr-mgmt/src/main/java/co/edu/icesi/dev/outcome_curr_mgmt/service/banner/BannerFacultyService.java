package co.edu.icesi.dev.outcome_curr_mgmt.service.banner;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BannerFacultyService {
    List<FacultyOutDTO> getBannerFaculties();
    Page<FacultyOutDTO> getBannerFacultiesPage(int page, int size);
    List<FacultyOutDTO> importBannerFaculties(List<String> facultiesNames);
}
