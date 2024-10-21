package co.edu.icesi.dev.outcome_curr.mgmt.rs.management;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="UsrBlockByAcadBlockWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultytId}/acad_programs/{acadPrgId}/course_blocks/{curBlockId}/users")
public interface AuthUsrBlockByAcadBlockController {

}
