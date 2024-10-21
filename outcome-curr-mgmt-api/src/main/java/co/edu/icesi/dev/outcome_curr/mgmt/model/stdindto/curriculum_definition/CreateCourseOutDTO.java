package co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_definition;

import lombok.Builder;

@Builder
public record CreateCourseOutDTO(String externalId, String courseNameEng, String courseNameSpa, int courseCredits,
                                 int hourlyIntensity, int weeklyIntensity) {

}
