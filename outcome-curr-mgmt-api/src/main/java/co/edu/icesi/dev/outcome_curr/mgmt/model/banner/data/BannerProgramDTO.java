package co.edu.icesi.dev.outcome_curr.mgmt.model.banner.data;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;

public record BannerProgramDTO(
        String acpId,
        String acpIsActive,
        String acpProgDescEng,
        String acpProgDescSpa,
        String acpProgNameEng,
        String acpProgNameSpa,
        Integer acpSnies,
        FacultyInDTO faculty
) {
}
