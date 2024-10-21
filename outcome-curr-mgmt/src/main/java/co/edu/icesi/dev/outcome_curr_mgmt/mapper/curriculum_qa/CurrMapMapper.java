package co.edu.icesi.dev.outcome_curr_mgmt.mapper.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.ValueDTO;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface CurrMapMapper {
    ValueDTO fromMapElementToValueDTO(Map.Entry<String, String> entry);
}
