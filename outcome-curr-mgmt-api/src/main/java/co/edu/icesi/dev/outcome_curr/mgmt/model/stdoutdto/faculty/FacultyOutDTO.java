package co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty;

import lombok.Builder;
import java.util.List;

@Builder
public record FacultyOutDTO(
    long facId,
    char facIsActive,
    String facNameEng,
    String facNameSpa,
    List<AcadProgramOutDTO> acadPrograms
){

}