package co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.AssmtGenPlanInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.AssmtGenPlanOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa.AuthAssessmentGlenPlanPermissions.*;

@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/acad_programs/{acadprogId}/assessemnt_plans")
public interface AdminAsssessmentGenPlanController {
    //TODO move to auth and remove
    String OK = "Ok";
    String NOT_FOUND = "Id for entity not found";
    String UNPROCESSABLE_ENTITY = "The entity does not provide the fields values that are required to fully meet the business requirements";
    String CONFLICT = "Incorrect faculty or program ID";
    String UN_AUTHORIZED = "User credentials are needed for this request";
    String ADMIN_ASSMNT_REQUIRED = "A permission of Admin-***-gen-assmnt-plan-prog or superior is required for this operation depending on the plan and status";
    String ADMIN_OWN_ASSMNT_REQUIRED = "A permission of Admin-***-gen-assmnt-plan-own or superior is required for this operation depending on the plan and status";
    String ADMIN_ASSMNT_STATUS_REQUIRED = "A permission of Update-***-gen-assmnt-plan is required for this operation depending on the plan";

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FUTURE_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_ADMIN_FUTURE_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_ADMIN_FUTURE_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_ADMIN_EXEC_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_ADMIN_EXEC_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_ADMIN_EXEC_GEN_ASSMNT_PLAN_PRG+"',"
            +"'"+ROLE_ADMIN_REVIEW_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_ADMIN_REVIEW_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_ADMIN_REVIEW_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_ADMIN_CLOSED_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_ADMIN_CLOSED_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_ADMIN_CLOSED_GEN_ASSMNT_PLAN_PRG+"')")
    @Operation(summary = "Create a new General Assessment plan")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AssmtGenPlanOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = ADMIN_ASSMNT_REQUIRED))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = CONFLICT))}),
            @ApiResponse(responseCode = "422", content = {
                    @Content(schema = @Schema($comment = UNPROCESSABLE_ENTITY))})})
    AssmtGenPlanOutDTO createAssessmentGenPlan(@PathVariable("facultyId") long facultyId,
            @PathVariable("acadprogId") long acadProgId, @Valid @RequestBody AssmtGenPlanInDTO assmtGenPlanInDTO);

    @PutMapping("/{asgplaId}")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FUTURE_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_ADMIN_FUTURE_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_ADMIN_FUTURE_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_ADMIN_EXEC_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_ADMIN_EXEC_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_ADMIN_EXEC_GEN_ASSMNT_PLAN_PRG+"',"
            +"'"+ROLE_ADMIN_REVIEW_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_ADMIN_REVIEW_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_ADMIN_REVIEW_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_ADMIN_CLOSED_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_ADMIN_CLOSED_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_ADMIN_CLOSED_GEN_ASSMNT_PLAN_PRG+"',"
            + "'"+ROLE_ADMIN_FUTURE_GEN_ASSMNT_PLAN_OWN+"','"+ROLE_ADMIN_EXEC_GEN_ASSMNT_PLAN_OWN+"',"
            + "'"+ROLE_ADMIN_REVIEW_GEN_ASSMNT_PLAN_OWN+"','"+ROLE_ADMIN_CLOSED_GEN_ASSMNT_PLAN_OWN+"')")
    @Operation(summary = "Updates a General Assessment Plan by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AssmtGenPlanOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = ADMIN_OWN_ASSMNT_REQUIRED))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = CONFLICT))}),
            @ApiResponse(responseCode = "422", content = {
                    @Content(schema = @Schema($comment = UNPROCESSABLE_ENTITY))})})
    void updateAssessmentGenPlanById(@PathVariable("facultyId") long facultyId,
            @PathVariable("acadprogId") long acadProgId, @PathVariable("asgplaId") long asgplaId,
            @Valid @RequestBody AssmtGenPlanInDTO assmtGenPlanInDTO);

    @PreAuthorize("hasAnyRole('"+ROLE_UPDATE_STATUS_FUTURE_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_UPDATE_STATUS_FUTURE_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_UPDATE_STATUS_FUTURE_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_UPDATE_STATUS_EXEC_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_UPDATE_STATUS_EXEC_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_UPDATE_STATUS_EXEC_GEN_ASSMNT_PLAN_PRG+"',"
            +"'"+ROLE_UPDATE_STATUS_REVIEW_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_UPDATE_STATUS_REVIEW_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_UPDATE_STATUS_REVIEW_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_UPDATE_STATUS_CLOSED_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_UPDATE_STATUS_CLOSED_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_UPDATE_STATUS_CLOSED_GEN_ASSMNT_PLAN_PRG+"')")
    @Operation(summary = "Updates the status of a General Assessment Plan")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AssmtGenPlanOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = ADMIN_ASSMNT_STATUS_REQUIRED))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = CONFLICT))}),
            @ApiResponse(responseCode = "422", content = {
                    @Content(schema = @Schema($comment = UNPROCESSABLE_ENTITY))})})
    void updateStatusAssmntGenPlan(@PathVariable("facultyId") long facultyId,
            @PathVariable("acadprogId") long acadProgId, @PathVariable("asgplaId") long asgplaId,
            String assessmentGenPlanType);

    @DeleteMapping("/{asgplaId}")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FUTURE_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_ADMIN_FUTURE_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_ADMIN_FUTURE_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_ADMIN_EXEC_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_ADMIN_EXEC_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_ADMIN_EXEC_GEN_ASSMNT_PLAN_PRG+"',"
            +"'"+ROLE_ADMIN_REVIEW_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_ADMIN_REVIEW_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_ADMIN_REVIEW_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_ADMIN_CLOSED_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_ADMIN_CLOSED_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_ADMIN_CLOSED_GEN_ASSMNT_PLAN_PRG+"')")
    @Operation(summary = "Delete a General Assessment Plan by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AssmtGenPlanOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = ADMIN_ASSMNT_STATUS_REQUIRED))}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = NOT_FOUND))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = CONFLICT))}),
            @ApiResponse(responseCode = "422", content = {
                    @Content(schema = @Schema($comment = UNPROCESSABLE_ENTITY))})})
    void deleteAssessmentGenPlanById(@PathVariable("facultyId") long facultyId,
            @PathVariable("acadprogId") long acadProgId, @PathVariable("asgplaId") long asgplaId);

}

