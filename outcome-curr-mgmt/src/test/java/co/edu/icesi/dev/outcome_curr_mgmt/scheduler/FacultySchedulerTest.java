package co.edu.icesi.dev.outcome_curr_mgmt.scheduler;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.FacultyRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.FacultyService;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.FacultyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FacultySchedulerTest {

    @Mock
    private FacultyRepository facultyRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private FacultyService facultyService;

    @Test
    void testSynchronizeFacultiesScheduledTask() {
        // Preparar datos de prueba
        List<FacultyOutDTO> mockFaculties = Arrays.asList(
                new FacultyOutDTO(1, 'Y', "Faculty of Engineering", "Facultad de Ingeniería", new ArrayList<>()),
                new FacultyOutDTO(2, 'Y', "Faculty of Health Sciences", "Facultad de Ciencias de la Salud", new ArrayList<>())
        );

        // Configurar comportamiento mock
        when(facultyService.getFaculties()).thenReturn(mockFaculties);

    }


}

