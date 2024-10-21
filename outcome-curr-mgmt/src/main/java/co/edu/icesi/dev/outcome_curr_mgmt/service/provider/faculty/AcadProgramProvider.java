package co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType;

public interface AcadProgramProvider {
    AcadProgramPermType.AcadProgramPermStatus getAcadProgramPermStatusOfAPeriodRange(
            AcPeriod startAcadPeriod, AcPeriod endAcadPeriod);
    AcadProgram findAcadProgram(long acadProgramId);
}
