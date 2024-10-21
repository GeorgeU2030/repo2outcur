package co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty;

import lombok.Builder;

@Builder
public record AcadProgramOutDTO (long acpId,
                                 char acpIsActive,
                                 String acpProgNameSpa,
                                 String acpProgNameEng,
                                 String acpProgDescSpa,
                                 String acpProgDescEng,
                                 String acpSnies){
}