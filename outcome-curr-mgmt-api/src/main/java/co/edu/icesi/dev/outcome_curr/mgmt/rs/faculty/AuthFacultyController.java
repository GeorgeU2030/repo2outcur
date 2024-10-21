package co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

import static co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthFacultyPermissions.*;

@Tag(name ="FacultyWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties")
public interface AuthFacultyController {
    String OK = "Ok";
    String NOT_FOUND = "Missing required fields";
    String UN_AUTHORIZED = "User credentials are needed for this request";
    String DUPLICATED_NAME_IN_SPA = "There is another faculty with the same name in Spanish";
    String DUPLICATED_NAME_IN_ENG = "There is another faculty with the same name in English";
    String FACULTY_NOT_DELETED = "A faculty cannot be deleted if has academic programs, courses or users associated.";

    @PostMapping("/")
    @Operation(summary = "Create a faculty")
    @PreAuthorize("hasRole('"+ROLE_ADMIN_FACULTIES_ANY+"')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FacultyOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = DUPLICATED_NAME_IN_SPA+" OR "+DUPLICATED_NAME_IN_ENG))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = "Required permissions for this operation: "+ ROLE_ADMIN_FACULTIES_ANY))})})
    FacultyOutDTO createFaculty
            (@Valid @RequestBody FacultyInDTO facultyInDTO);

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FACULTIES_ANY+"','"+ROLE_QUERY_FACULTIES_ANY+"')")
    @Operation(summary = "Get the faculties")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FacultyOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = "Required permissions for this operation: "+ROLE_ADMIN_FACULTIES_ANY+" AND "+ROLE_QUERY_FACULTIES_ANY))})})
    List<FacultyOutDTO> getFaculties();

    @GetMapping("/{facultyId}")
    @Operation(summary = "Get a faculty by Id")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FACULTIES_ANY+"','"+ROLE_QUERY_FACULTIES_ANY+"','"+ROLE_ADMIN_FACULTIES_OWN+"','"+ROLE_QUERY_FACULTIES_OWN+"')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FacultyOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = "Required permissions for this operation: "+ROLE_ADMIN_FACULTIES_ANY+" OR "+ROLE_QUERY_FACULTIES_ANY+" OR "+ROLE_ADMIN_FACULTIES_OWN+" OR "+ROLE_QUERY_FACULTIES_OWN))})})
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = "Faculty not found"))})
    FacultyOutDTO getFacultyByFacId(@PathVariable("facultyId") long facultyId);

    @GetMapping("/nameInSpa/{facultySpaName}")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FACULTIES_ANY+"','"+ROLE_QUERY_FACULTIES_ANY+"','"+ROLE_ADMIN_FACULTIES_OWN+"','"+ROLE_QUERY_FACULTIES_OWN+"')")
    @Operation(summary = "Get a faculty by its name in Spanish")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FacultyOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = "Required permissions for this operation: "+ROLE_ADMIN_FACULTIES_ANY+" OR "+ROLE_QUERY_FACULTIES_ANY+" OR "+ROLE_ADMIN_FACULTIES_OWN+" OR "+ROLE_QUERY_FACULTIES_OWN))})})
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = "Faculty not found"))})
    FacultyOutDTO getFacultyByFacNameInSpa(@PathVariable("facultySpaName") String name);

    @GetMapping("/nameInEng/{facultyEngName}")
    @PreAuthorize("hasAnyRole('"+ROLE_ADMIN_FACULTIES_ANY+"','"+ROLE_QUERY_FACULTIES_ANY+"','"+ROLE_ADMIN_FACULTIES_OWN+"','"+ROLE_QUERY_FACULTIES_OWN+"')")
    @Operation(summary = "Get a faculty by its name in English")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FacultyOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = "Required permissions for this operation: "+ROLE_ADMIN_FACULTIES_ANY+" OR "+ROLE_QUERY_FACULTIES_ANY+" OR "+ROLE_ADMIN_FACULTIES_OWN+" OR "+ROLE_QUERY_FACULTIES_OWN))})})
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = "Faculty not found"))})
    FacultyOutDTO getFacultyByFacNameInEng(@PathVariable("facultyEngName") String name);

    @PutMapping("/{facultyId}")
    @Operation(summary = "Update a faculty")
    @PreAuthorize("hasRole('"+ROLE_ADMIN_FACULTIES_ANY+"')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = FacultyOutDTO.class, $comment = OK), mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = "Required permissions for this operation: "+ ROLE_ADMIN_FACULTIES_ANY))}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = "Faculty not found"))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = DUPLICATED_NAME_IN_SPA+" OR "+DUPLICATED_NAME_IN_ENG))})})
    FacultyOutDTO updateFaculty
            (@PathVariable("facultyId") long facultyId, @Valid @RequestBody FacultyInDTO facultyInDTO);

    @DeleteMapping("/{facultyId}")
    @Operation(summary = "Delete a faculty")
    @PreAuthorize("hasRole('"+ROLE_ADMIN_FACULTIES_ANY+"')")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Faculty deleted successfully"),
            @ApiResponse(responseCode = "401", content = {
                    @Content(schema = @Schema($comment = UN_AUTHORIZED))}),
            @ApiResponse(responseCode = "403", content = {
                    @Content(schema = @Schema($comment = "Required permissions for this operation: "+ ROLE_ADMIN_FACULTIES_ANY))}),
            @ApiResponse(responseCode = "409", content = {
                    @Content(schema = @Schema($comment = FACULTY_NOT_DELETED))}),
            @ApiResponse(responseCode = "404", content = {
                    @Content(schema = @Schema($comment = "Faculty not found"))})})
    ResponseEntity<Void> deleteFaculty(@PathVariable("facultyId") long facultyId);
}