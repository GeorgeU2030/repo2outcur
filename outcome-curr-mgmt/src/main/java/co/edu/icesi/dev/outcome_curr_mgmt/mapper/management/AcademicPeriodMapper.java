package co.edu.icesi.dev.outcome_curr_mgmt.mapper.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.AcadPeriodInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.AcadPeriodOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import org.mapstruct.Mapper;

@Mapper
public interface AcademicPeriodMapper {

    AcPeriod fromAcadPeriodInDTO(AcadPeriodInDTO sourceAcadPeriodInDTO);

    AcadPeriodOutDTO fromAcadPeriod(AcPeriod sourceAcademicPeriod);

}
