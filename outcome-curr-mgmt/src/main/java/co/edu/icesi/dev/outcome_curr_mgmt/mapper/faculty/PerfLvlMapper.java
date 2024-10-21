package co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.PerfLvlInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.PerfLvlOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.PerfLvl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PerfLvlMapper {
    PerfLvl fromPerfLvlInDTO(PerfLvlInDTO perfLvlInDTO);

    @Mapping(target = "acadProgramId", expression = "java(perfLvl.getAcadProgram().getAcpId())")
    PerfLvlOutDTO fromPerfLvl(PerfLvl perfLvl);

}
