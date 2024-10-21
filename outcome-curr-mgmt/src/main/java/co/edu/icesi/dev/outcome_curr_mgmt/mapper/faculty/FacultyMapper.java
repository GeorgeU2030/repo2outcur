package co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface FacultyMapper {

    @Mapping(target = "facIsActive", expression = "java(facultyInDTO.isActive().charAt(0))")
    @Mapping(target = "externalId", source = "externalId")
    Faculty facultyInDTOToFaculty(FacultyInDTO facultyInDTO);

    FacultyOutDTO facultyToFacultyOutDTO(Faculty faculty);

    List<FacultyOutDTO> facultiesToFacultiesOutDTO(List<Faculty> all);

    @Mapping(target = "facIsActive", expression = "java(facultyInDTO.isActive().charAt(0))")
    FacultyOutDTO facultyInDTOToFacultyOutDTO(FacultyInDTO facultyInDTO);
}
