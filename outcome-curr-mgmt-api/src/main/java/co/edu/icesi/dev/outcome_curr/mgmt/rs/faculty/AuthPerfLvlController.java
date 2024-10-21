package co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty;


import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.PerfLvlInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.PerfLvlOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthAcadProgramPermissions.*;

@Tag(name ="PerformanceLevelWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/acad_programs/{acadProgId}/performance_levels")
public interface AuthPerfLvlController {

    String OK = "Ok";
    String NOT_FOUND = "Id for entity not found";
    String CONFLICT = "Incorrect faculty or program ID";
    String BAD_REQUEST = "Missing required fields";
    String UN_AUTHORIZED = "User credentials are needed for this request";
    String QUERY_ACAD_PROGRAM_REQUIRED = "A permission of Query-***-acad_program or superior is required for this operation";

    String ADMIN_ACAD_PROGRAM_REQUIRED = "A permission of Admin-***-acad_program or superior is required for this operation";

    @GetMapping("/{perfLvlId}")
    @PreAuthorize("hasAnyRole('"+ROLE_QUERY_FUTURE_ACADPROGRAMS_ANY+"','"+ROLE_QUERY_FUTURE_ACADPROGRAMS_FAC+"',"
            + "'"+ROLE_QUERY_FUTURE_ACADPROGRAMS_OWN+"','"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_ANY+"',"
            +"'"+ROLE_QUERY_CURRENT_ACADPROGRAMS_FAC+"','"+ROLE_QUERY_CURRENT_ACADPROGRAMS_OWN+"',"
            +"'"+ROLE_QUERY_INACTIVE_ACADPROGRAMS_ANY+"','"+ROLE_QUERY_INACTIVE_ACADPROGRAMS_FAC+"',"
            +"'"+ROLE_QUERY_INACTIVE_ACADPROGRAMS_OWN+"')")
    @ApiOperation("Get a Performance Level by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PerfLvlOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema($comment = QUERY_ACAD_PROGRAM_REQUIRED))}),
            @ApiResponse(responseCode = "409", content = {@Content(schema = @Schema($comment = CONFLICT))}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema($comment = NOT_FOUND))}),
            })
    PerfLvlOutDTO getPerfLvl(@PathVariable long perfLvlId,@PathVariable long acadProgId,@PathVariable long facultyId);

    @GetMapping
    @PreAuthorize("hasAnyRole('"+ROLE_QUERY_FUTURE_ACADPROGRAMS_ANY+"','"+ROLE_QUERY_FUTURE_ACADPROGRAMS_FAC+"',"
            + "'"+ROLE_QUERY_FUTURE_ACADPROGRAMS_OWN+"','"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_ANY+"',"
            +"'"+ROLE_QUERY_CURRENT_ACADPROGRAMS_FAC+"','"+ROLE_QUERY_CURRENT_ACADPROGRAMS_OWN+"',"
            +"'"+ROLE_QUERY_INACTIVE_ACADPROGRAMS_ANY+"','"+ROLE_QUERY_INACTIVE_ACADPROGRAMS_FAC+"',"
            +"'"+ROLE_QUERY_INACTIVE_ACADPROGRAMS_OWN+"')")
    @ApiOperation("Get the Performance Levels from an Academic Program")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PerfLvlOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema($comment = QUERY_ACAD_PROGRAM_REQUIRED))}),
            @ApiResponse(responseCode = "409", content = {@Content(schema = @Schema($comment = CONFLICT))}),
            })
    List<PerfLvlOutDTO> getAllPerfLvls(@PathVariable long acadProgId,@PathVariable long facultyId);

    @PostMapping
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FUTURE_ACADPROGRAMS_ANY+"','"+ROLE_ADMIN_FUTURE_ACADPROGRAMS_FAC+"',"
            + "'"+ROLE_ADMIN_FUTURE_ACADPROGRAMS_OWN+"','"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_ANY+"',"
            + "'"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_FAC+"','"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_OWN+"',"
            + "'"+ROLE_ADMIN_INACTIVE_ACADPROGRAMS_ANY+"','"+ROLE_ADMIN_INACTIVE_ACADPROGRAMS_FAC+"',"
            + "'"+ROLE_ADMIN_INACTIVE_ACADPROGRAMS_OWN+"')")
    @Operation(summary = "Create a new Performance Level for an Academic Program")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = PerfLvlOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = ADMIN_ACAD_PROGRAM_REQUIRED))}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema($comment = BAD_REQUEST))}),
            @ApiResponse(responseCode = "409", content = {@Content(schema = @Schema($comment = CONFLICT))})})
    PerfLvlOutDTO addPerfLvl(@Valid @RequestBody PerfLvlInDTO perfLvlInDTO, @PathVariable long acadProgId,
            @PathVariable long facultyId);

    @PutMapping("/{perfLvlId}")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FUTURE_ACADPROGRAMS_ANY+"','"+ROLE_ADMIN_FUTURE_ACADPROGRAMS_FAC+"',"
            + "'"+ROLE_ADMIN_FUTURE_ACADPROGRAMS_OWN+"','"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_ANY+"',"
            + "'"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_FAC+"','"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_OWN+"',"
            + "'"+ROLE_ADMIN_INACTIVE_ACADPROGRAMS_ANY+"','"+ROLE_ADMIN_INACTIVE_ACADPROGRAMS_FAC+"',"
            + "'"+ROLE_ADMIN_INACTIVE_ACADPROGRAMS_OWN+"')")
    @Operation(summary = "Update a Performance Level for an Academic Program")
    @ApiResponses({@ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = PerfLvlOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = ADMIN_ACAD_PROGRAM_REQUIRED))}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema($comment = BAD_REQUEST))}),
            @ApiResponse(responseCode = "409", content = {@Content(schema = @Schema($comment = CONFLICT))})})
    PerfLvlOutDTO updatePerfLvl(@Valid @RequestBody PerfLvlInDTO perfLvlInDTONew, @PathVariable long acadProgId,
            @PathVariable long facultyId, @PathVariable long perfLvlId);


    @DeleteMapping("/{perfLvlId}")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FUTURE_ACADPROGRAMS_ANY+"','"+ROLE_ADMIN_FUTURE_ACADPROGRAMS_FAC+"',"
            + "'"+ROLE_ADMIN_FUTURE_ACADPROGRAMS_OWN+"','"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_ANY+"',"
            + "'"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_FAC+"','"+ROLE_ADMIN_CURRENT_ACADPROGRAMS_OWN+"',"
            + "'"+ROLE_ADMIN_INACTIVE_ACADPROGRAMS_ANY+"','"+ROLE_ADMIN_INACTIVE_ACADPROGRAMS_FAC+"',"
            + "'"+ROLE_ADMIN_INACTIVE_ACADPROGRAMS_OWN+"')")
    @Operation(summary = "Delete a Performance Level for an Academic Program")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Performance LEVEL deleted"),
            @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = ADMIN_ACAD_PROGRAM_REQUIRED))}),
            @ApiResponse(responseCode = "409", content = {@Content(schema = @Schema($comment = CONFLICT))})})
    ResponseEntity<Void> deletePerfLvl(@PathVariable long acadProgId, @PathVariable long facultyId, @PathVariable long perfLvlId);
}
