package co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.AssmtGenPlanOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa.AuthAssessmentGlenPlanPermissions.*;

@Tag(name = "AssessmentGenPlanWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/acad_programs/{acadprogId}/assessemnt_plans")
public interface AuthAssessmentGenPlanController {
    String OK = "Ok";
    String NOT_FOUND = "Id for entity not found";
    String CONFLICT = "Incorrect faculty or program ID";
    String BAD_REQUEST = "Missing required fields";
    String UN_AUTHORIZED = "User credentials are needed for this request";
    String QUERY_ASSMNT_REQUIRED = "A permission of Query-***-gen-assmnt-plan-prg or superior is required for this operation depending on the plan";
    String QUERY_OWN_ASSMNT_REQUIRED = "A permission of Query-***-gen-assmnt-plan-own or superior is required for this operation depending on the plan";

    //TODO add Admin permissions for Get endpoints
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('"+ROLE_QUERY_FUTURE_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_QUERY_FUTURE_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_QUERY_FUTURE_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_QUERY_EXEC_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_QUERY_EXEC_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_QUERY_EXEC_GEN_ASSMNT_PLAN_PRG+"',"
            +"'"+ROLE_QUERY_REVIEW_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_QUERY_REVIEW_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_QUERY_REVIEW_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_QUERY_CLOSED_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_QUERY_CLOSED_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_QUERY_CLOSED_GEN_ASSMNT_PLAN_PRG+"')")
    @Operation(summary = "Get the General Assessment plans")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AssmtGenPlanOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = QUERY_OWN_ASSMNT_REQUIRED))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = CONFLICT))})})
    List<AssmtGenPlanOutDTO> getAssmntGenPlan(@PathVariable("facultyId") long facultyId,
            @PathVariable("acadprogId") long acadProgId);

    @GetMapping("/{asgplaId}")
    @PreAuthorize("hasAnyRole('"+ROLE_QUERY_FUTURE_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_QUERY_FUTURE_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_QUERY_FUTURE_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_QUERY_EXEC_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_QUERY_EXEC_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_QUERY_EXEC_GEN_ASSMNT_PLAN_PRG+"',"
            +"'"+ROLE_QUERY_REVIEW_GEN_ASSMNT_PLAN_ANY+"','"+ROLE_QUERY_REVIEW_GEN_ASSMNT_PLAN_FAC+"',"
            + "'"+ROLE_QUERY_REVIEW_GEN_ASSMNT_PLAN_PRG+"','"+ROLE_QUERY_CLOSED_GEN_ASSMNT_PLAN_ANY+"',"
            + "'"+ROLE_QUERY_CLOSED_GEN_ASSMNT_PLAN_FAC+"','"+ROLE_QUERY_CLOSED_GEN_ASSMNT_PLAN_PRG+"')")
    @Operation(summary = "Get a General Assessment Plan by Id")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AssmtGenPlanOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = QUERY_OWN_ASSMNT_REQUIRED))}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = NOT_FOUND))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = CONFLICT))})})
    AssmtGenPlanOutDTO getAssmntGenPlanById(@PathVariable("facultyId") long facultyId,
            @PathVariable("acadprogId") long acadProgId, @PathVariable("asgplaId") long asgplaId);

}

