package co.edu.icesi.dev.outcome_curr_mgmt.service.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.AcadPeriodInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.AcadPeriodOutDTO;

import java.util.List;

public interface AcPeriodService {

    AcadPeriodOutDTO addAcademicPeriod(AcadPeriodInDTO academicPeriodToCreate);

    AcadPeriodOutDTO searchAcademicPeriod(Long acadPeriodId);

    AcadPeriodOutDTO searchAcademicPeriodByNumber(int academicPeriodNumber);

    AcadPeriodOutDTO searchAcademicPeriodBySpaName(String academicPeriodSpaName);

    List<AcadPeriodOutDTO> getAllAcademicPeriods();

    AcadPeriodOutDTO setAcademicPeriod(Long acadPeriodId, AcadPeriodInDTO acadPeriodInDTO);

    void deleteAcademicPeriod(Long acadPeriodId);
}
