package co.edu.icesi.dev.outcome_curr_mgmt.mapper.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.AssmtGenPlanInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.AssmtGenPlanOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtGenPlan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AssmtGenPlanMapper {
    AssmtGenPlan assmtGenPlanInDTOToAssmtGenPlan(AssmtGenPlanInDTO assmtGenPlanInDTO);

    AssmtGenPlanOutDTO assmtGenPlanToAssmtGenPlanOutDTO(AssmtGenPlan assmtGenPlan);

    List<AssmtGenPlanOutDTO> assmtGenPlansToAssmtGenPlanOutDTOs(List<AssmtGenPlan> assmtGenPlan);

    void updateAssmtGenPlan(AssmtGenPlanInDTO assmtGenPlanInDTO,
            @MappingTarget AssmtGenPlan assmtGenPlan);
}
