package co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

@Builder
public record AssmtGenPlanInDTO(@ApiModelProperty(required = true) long startAcadPeriod,
                                @ApiModelProperty(required = true) long endAcadPeriod,
                                @ApiModelProperty(required = true) long numberCycles,
                                @ApiModelProperty(required = true) long subCyclesPerCycles,
                                @ApiModelProperty long previousAssmtGenPlan) {
}