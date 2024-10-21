package co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa;

import lombok.Builder;

@Builder
public record PerfLvlOutDTO(
        long plId,
        String plNameEng,
        String plNameSpa,
        int plOrder,
        char plIsActive,
        long acadProgramId

) {
}
