package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.AcadProgramInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.AcadProgramMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.AcadProgramRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType;
import co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty.FacultyProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.AcadProgramValidator;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType.AcadProgramPermStatus.CURRENT;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"acadProgram"})
public class AcadProgramServiceImpl implements AcadProgramService {
    //TODO add test coverage
    private static final Logger logger = LoggerFactory.getLogger(AcadProgramServiceImpl.class);

    private final AcadProgramValidator acadProgramValidator;

    private final AcadProgramRepository acadProgramRepository;

    private final FacultyProvider facultyProvider;

    private final AcadProgramMapper acadProgramMapper;

    //TODO the program should not assume the operations are for CURRENT programs. It should also support Future
    // and Inactive, filterin according to the parameter used as input. Use the logger for errors.

    @Transactional
    @Override
    public List<AcadProgram> getAcadProgramsByFaculty(long facultyId) {
        validateAccess(facultyId, 0L, UserPermAccess.QUERY, CURRENT);
        List<AcadProgram> products = acadProgramRepository.findAllByFacultyFacId(facultyId);
        //TODO validate the faculty exists instead of empty
        if (products.isEmpty()) {
            throw new OutCurrException(OutCurrExceptionType.FACULTY_INVALID_FAC_ID);
        }
        return products;
    }

    @Transactional
    @Override
    public AcadProgramOutDTO getAcadProgram(long facultyId, long acadProgramId) {
        validateAccess(facultyId, acadProgramId, UserPermAccess.QUERY, CURRENT);
        return acadProgramMapper.acadProgramToAcadProgramOutDto(findAcadProgram(facultyId, acadProgramId));
    }

    //TODO enable AspectJ for non-injected cache calls, change visibility to non-public
    @Cacheable(key = "#acadProgramId")
    public AcadProgram findAcadProgram(long facultyId, long acadProgramId) {
        // TODO: validate acadProgram is in faculty.
        return acadProgramRepository.findByAcpId(acadProgramId)
                .orElseThrow(() -> new OutCurrException(OutCurrExceptionType.PROGACAD_INVALID_PROGRAM_ID));
    }

    @Transactional
    @Override
    public AcadProgramOutDTO createAcadProgram(long facultyId, AcadProgramInDTO acadProgramInDTO) {
        validateAccess(facultyId, 0L, UserPermAccess.ADMIN, CURRENT);
        Faculty faculty = facultyProvider.findFacultyByFacId(facultyId);
        AcadProgram acadProgram = acadProgramMapper.acadProgramInDTOToAcadProgram(acadProgramInDTO);
        acadProgram.setFaculty(faculty);
        return acadProgramMapper.acadProgramToAcadProgramOutDto(acadProgramRepository.save(acadProgram));
    }

    @Transactional
    @Override
    @CachePut(key = "#facultyId")
    public void updateAcadProgram(long facultyId, long acadProgramId, AcadProgramInDTO acadProgramInDTO) {
        //TODO validate the faculty
        // TODO: validate acadProgram is in faculty. Throw exception if program does not exists
        validateAccess(facultyId, acadProgramId, UserPermAccess.ADMIN, CURRENT);
        AcadProgram acadProgram = findAcadProgram(facultyId, acadProgramId);
        acadProgramMapper.updateAcadProgram(acadProgramInDTO, acadProgram);
        acadProgramRepository.save(acadProgram);
    }

    @Transactional
    @Override
    @CacheEvict(key = "#acadProgramId")
    public void deleteAcadProgram(long facultyId, long acadProgramId) {
        //TODO validate the faculty
        // TODO: validate acadProgram is in faculty. Throw exception if program does not exists
        validateAccess(facultyId, acadProgramId, UserPermAccess.ADMIN, CURRENT);
        AcadProgram acadProgram = findAcadProgram(facultyId, acadProgramId);
        acadProgramRepository.delete(acadProgram);
    }

    private void validateAccess(long facultyId, long acadProgId, UserPermAccess permAccess,
            AcadProgramPermType.AcadProgramPermStatus permStatus) {
        acadProgramValidator.enforceUsrFacForAcadProgram(facultyId, permAccess, permStatus);
        acadProgramValidator.enforceUsrPrgForAcadProgram(acadProgId, permAccess, permStatus);
    }

}
