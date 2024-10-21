package co.edu.icesi.dev.outcome_curr_mgmt.rs.banner;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyNamesRequestDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.banner.BannerFacultyImportController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.banner.BannerFacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BannerFacultyImportControllerImpl implements BannerFacultyImportController {

    private final BannerFacultyService bannerFacultyService;

    @Override
    public List<FacultyOutDTO> getFacultiesList() {
        return bannerFacultyService.getBannerFaculties();
    }

    @Override
    public Page<FacultyOutDTO> getFacultiesPage(Integer page, Integer size) {
        return bannerFacultyService.getBannerFacultiesPage(page, size);
    }

    @Override
    public List<FacultyOutDTO> importFaculties(FacultyNamesRequestDTO facultyNamesRequestDTO) {
        return bannerFacultyService.importBannerFaculties(facultyNamesRequestDTO.facultiesName());
    }
}
