package co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

@Builder
public record AcadProgramInDTO(@ApiModelProperty(required = true) long acpId,
                               @ApiModelProperty(required = true) String acpSnies,
                               @ApiModelProperty(required = true) String acpProgNameEng,
                               @ApiModelProperty(required = true) String acpProgDescEng,
                               @ApiModelProperty(required = true) String acpProgNameSpa,
                               @ApiModelProperty(required = true) String acpProgDescSpa,
                               @ApiModelProperty(required = true) char acpIsActive) {
}