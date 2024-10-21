package co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.AcadProgramInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AcadProgramMapper {
    void updateAcadProgram(AcadProgramInDTO acadProgramInDTO, @MappingTarget AcadProgram acadProgram);

    AcadProgram acadProgramInDTOToAcadProgram(AcadProgramInDTO acadProgramInDTO);

    AcadProgramOutDTO acadProgramToAcadProgramOutDto(AcadProgram acadProgramOutDTO);

    List<AcadProgramOutDTO> acadProgramsToAcadProgramOutDtos(List<AcadProgram> acadProgramOutDTO);

}
