package co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="PiLevelCategoryWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facid}/acad_programs/{acad_programId}/pi_level_categories/")
public interface AuthPiLvlCategController {

}
