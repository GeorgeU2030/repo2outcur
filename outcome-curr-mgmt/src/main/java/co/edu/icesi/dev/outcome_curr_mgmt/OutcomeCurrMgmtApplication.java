package co.edu.icesi.dev.outcome_curr_mgmt;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrFac;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrFacPK;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.AcadProgramRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.FacultyRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.AcPeriodRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UserRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrFacRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class OutcomeCurrMgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutcomeCurrMgmtApplication.class, args);
    }

          @Bean
          @Profile("!test")
          @Transactional
          CommandLineRunner commandLineRunner(
    //            PiLvlCategRepository piLvlCategRepository,
    //            StudOutcomeRepository studOutcomeRepository,
    //            PerfIndicatorRepository perfIndicatorRepository,
    //            CourseRepository courseRepository,
    //            CurrMapRepository currMapRepository,
    //            AssessmentTypeRepository assessmentTypeRepository,
                  AcadProgramRepository acadProgramRepository,
    //            AcadProgCurriculumRepository acadProgCurriculumRepository,
                  FacultyRepository facultyRepository,
    //            SemesterRepository semesterRepository,
    //            UserRepository userRepository
                  AcPeriodRepository acPeriodRepository
          ) {
    //        User testUser = User.builder().usrName("OutcurrTest1").build();
              Faculty testFaculty1 = Faculty.builder()
                      .facIsActive('Y')
                      .facNameEng("Faculty of Engineering")
                      .facNameSpa("Facultad de Ingeniería")
                      .build();
              Faculty testFaculty2 = Faculty.builder()
                      .facIsActive('Y')
                      .facNameEng("Faculty of Health Sciences")
                      .facNameSpa("Facultad de Ciencias de la Salud")
                      .build();

              AcadProgram acadProgram = AcadProgram.builder()
                    .acpIsActive('Y')
                    .acpProgDescEng("Software engineering")
                    .acpProgDescSpa("Ingeniería de sistemas")
                    .acpProgNameEng("Software engineering")
                    .acpProgNameSpa("Ingeniería de sistemas")
                    .acpSnies("123456789")
                    .build();

              AcPeriod acPeriod1= AcPeriod.builder()
                      .acPeriodNumeric(202302)
                      .acPeriodNameSpa("Periodo academico 2023-2")
                      .acPeriodNameEng("Academic period 2023-2")
                      .build();
              AcPeriod acPeriod2=AcPeriod.builder()
                      .acPeriodNumeric(202401)
                      .acPeriodNameSpa("Periodo academico 2024-1")
                      .acPeriodNameEng("Academic period 2024-1")
                      .build();
    //
    //        AcadProgCurriculum acadProgCurriculum = AcadProgCurriculum.builder()
    //                .apcNameEng("Software engineering curriculum for the years 2017-2021")
    //                .apcNameSpa("Plan de estudios de ingeniería de sistemas para los años 2017-2021")
    //                .build();
    //
    //        StudOutcome studOutcome1 = StudOutcome.builder()
    //                .soIsActive('Y')
    //                .soLongNameEng("SO1 - Problem solving")
    //                .soLongNameSpa("SO1 - Solución de problemas: Identificar, formular y resolver problemas complejos de ingeniería aplicando pensamiento crítico y principios de las ciencias, las matemáticas, la ingeniería y, en particular, de las Ciencias de la Computación y de la Ingeniería de Software.")
    //                .soShortNameEng("SO1 - Problem solving")
    //                .soShortNameSpa("SO1 - Solución de problemas")
    //                .soOrdinalNumber(1)
    //                .build();
    //
    //        StudOutcome studOutcome2 = StudOutcome.builder()
    //                .soIsActive('Y')
    //                .soLongNameEng("SO2 - Design")
    //                .soLongNameSpa("SO2 – Diseño de Ingeniería: Diseñar soluciones y procesos basados en software que satisfagan necesidades específicas y generen valor a sus usuarios, considerando la salud pública, la seguridad y el bienestar de las personas, así como factores globales, culturales, sociales, ambientales y económicos.")
    //                .soShortNameEng("SO2 - Design")
    //                .soShortNameSpa("SO2 – Diseño de Ingeniería")
    //                .soOrdinalNumber(2)
    //                .build();
    //
    //        PerfIndicator perfIndicator1SO1 = PerfIndicator.builder()
    //                .piShortNameEng("1-PI1 Problem identification and formulation")
    //                .piShortNameSpa("SO1-PI1 Identificación y formulación de problemas")
    //                .piLongNameEng("1-PI1 Problem identification and formulation: to identify a complex problem and formulate it, by applying principles of engineering (the engineering method).")
    //                .piLongNameSpa("SO1-PI1 Identificación y formulación de problemas: Identificar un problema complejo y formularlo, aplicando principios de ingeniería y el método de ingeniería.")
    //                .piOrdinalNumber(1)
    //                .build();
    //
    //        PerfIndicator perfIndicator2SO1 = PerfIndicator.builder()
    //                .piShortNameEng("1-PI2 Problem solution (engineering/science)")
    //                .piShortNameSpa("SO1-PI2 Solución de problema (ingeniería/ciencias)")
    //                .piLongNameEng("1-PI2 Problem solution (engineering/science): to solve complex problems by proposing a strategy compatible with the formulation and by applying principles of engineering or science.")
    //                .piLongNameSpa("SO1-PI2 Solución de problema (ingeniería/ciencias): Resolver problemas complejos proponiendo una estrategia compatible con la formulación y aplicando principios de ingeniería o ciencias.")
    //                .piOrdinalNumber(2)
    //                .build();
    //
    //        PerfIndicator perfIndicator3SO1 = PerfIndicator.builder()
    //                .piShortNameEng("1-PI3 Problem solution (mathematics)")
    //                .piShortNameSpa("SO1-PI3 Solución del problema (matemáticas)")
    //                .piLongNameEng("1-PI3 Problem solution (mathematics): to solve complex problems by proposing strategies compatible with their formulation and by applying mathematics.")
    //                .piLongNameSpa("SO1-PI3 Solución del problema (matemáticas): Resolver problemas complejos proponiendo estrategias compatibles con su formulación y aplicando matemáticas.")
    //                .piOrdinalNumber(3)
    //                .build();
    //
    //        PerfIndicator perfIndicator1SO2 = PerfIndicator.builder()
    //                .piShortNameEng("2-PI1 Detailed Design")
    //                .piShortNameSpa("SO2-PI1 Diseño detallado")
    //                .piLongNameEng("2-PI1 Detailed Design: To produce detailed design specifications that meet functional requirements, correctly using the corresponding tools and notations.")
    //                .piLongNameSpa("SO2-PI1 Diseño detallado: Producir especificaciones de diseño detalladas que cumplan con los requerimientos funcionales, utilizando correctamente las herramientas y notaciones correspondientes.")
    //                .piOrdinalNumber(1)
    //                .build();
    //
    //        PerfIndicator perfIndicator2SO2 = PerfIndicator.builder()
    //                .piShortNameEng("2-PI2 High Level Design")
    //                .piShortNameSpa("SO2-PI2 Diseño de alto nivel")
    //                .piLongNameEng("2-PI2 High Level Design: To produce architectural specifications, by correctly using the corresponding tools and notations, that meet functional and non-functional requirements, as well as consider public health, safety, welfare, as well as global, cultural, social, environmental, and economic factors, when relevant.")
    //                .piLongNameSpa("SO2-PI2 Diseño de alto nivel: Producir especificaciones arquitectónicas mediante el uso correcto de las herramientas y notaciones correspondientes, que cumplan con los requerimientos funcionales y no funcionales, además de considerar la salud pública, la seguridad, el bienestar, así como los factores globales, culturales, sociales, ambientales y económicos, cuando sea relevante.")
    //                .piOrdinalNumber(2)
    //                .build();
    //
    //        PerfIndicator perfIndicator3SO2 = PerfIndicator.builder()
    //                .piShortNameEng("2-PI3 Evaluation of solution design")
    //                .piShortNameSpa("SO2-PI3 Evaluación de diseño de soluciones")
    //                .piLongNameEng("2-PI3 Evaluation of solution design: To evaluate architectural specifications with respect to specified needs and most significant concerns (among non-functional requirements and aspects such as public health, safety, welfare, as well as global, cultural, social, environmental, and economic factors).")
    //                .piLongNameSpa("SO2-PI3 Evaluación de diseño de soluciones: Evaluar las especificaciones arquitectónicas con respecto a las necesidades específicas y las preocupaciones más importantes (entre los requerimientos no funcionales y aspectos como la salud pública, la seguridad, el bienestar, así como los factores globales, culturales, sociales, ambientales y económicos).")
    //                .piOrdinalNumber(3)
    //                .build();
    //
    //        Semester semester4 = Semester.builder()
    //                .semName("4")
    //                .build();
    //
    //        Semester semester5 = Semester.builder()
    //                .semName("5")
    //                .build();
    //
    //        Course course1 = Course.builder()
    //                .courseNameEng("Internet Computing I")
    //                .courseNameSpa("Computación en internet I")
    //                .build();
    //
    //        Course course2 = Course.builder()
    //                .courseNameEng("Capstone project I")
    //                .courseNameSpa("Proyecto Integrador I")
    //                .build();
    //
    //        AssessmentType formativeAssessmentType = AssessmentType.builder()
    //                .atNameSpa("formativo")
    //                .atNameEng("formative")
    //                .build();
    //
    //        PiLvlCateg introduce = PiLvlCateg.builder()
    //                .categIsActive('Y')
    //                .categNameEng("Introduce")
    //                .categNameSpa("Introducir")
    //                .categPosition(1)
    //                .build();
    //
    //        PiLvlCateg teach = PiLvlCateg.builder()
    //                .categIsActive('Y')
    //                .categNameEng("Teach")
    //                .categNameSpa("Enseñar")
    //                .categPosition(2)
    //                .build();
    //
    //        PiLvlCateg apply = PiLvlCateg.builder()
    //                .categIsActive('Y')
    //                .categNameEng("Apply")
    //                .categNameSpa("Aplicar")
    //                .categPosition(3)
    //                .build();
    //
              return args -> {
                  AcPeriod acadPeriod1= acPeriodRepository.save(acPeriod1);
                  AcPeriod acadPeriod2= acPeriodRepository.save(acPeriod2);

                  facultyRepository.save(testFaculty1);
                  facultyRepository.save(testFaculty2);

                  acadProgram.setFaculty(testFaculty1);
                  acadProgram.setStartAcPeriod(acadPeriod1);
                  acadProgram.setEndAcPeriod(acadPeriod2);
                  AcadProgram acadProgramSaved = acadProgramRepository.save(acadProgram);
    //            acadProgCurriculum.setAcadProgram(acadProgramSaved);
    //            AcadProgCurriculum  acadProgCurriculumSaved = acadProgCurriculumRepository.save(acadProgCurriculum);
    //
    //            studOutcome1.setAcadProgCurriculums(List.of(acadProgCurriculumSaved));
    //            studOutcome2.setAcadProgCurriculums(List.of(acadProgCurriculumSaved));
    //            StudOutcome studOutcome2Saved = studOutcomeRepository.save(studOutcome2);
    //            StudOutcome studOutcome1Saved = studOutcomeRepository.save(studOutcome1);
    //            perfIndicator1SO1.setStudOutcome(studOutcome1Saved);
    //            perfIndicator2SO1.setStudOutcome(studOutcome1Saved);
    //            perfIndicator3SO1.setStudOutcome(studOutcome1Saved);
    //            perfIndicatorRepository.save(perfIndicator1SO1);
    //            PerfIndicator perfIndicator2SO1Saved = perfIndicatorRepository.save(perfIndicator2SO1);
    //            perfIndicatorRepository.save(perfIndicator3SO1);
    //            perfIndicator1SO2.setStudOutcome(studOutcome2Saved);
    //            perfIndicator2SO2.setStudOutcome(studOutcome2Saved);
    //            perfIndicator3SO2.setStudOutcome(studOutcome2Saved);
    //            PerfIndicator perfIndicator1SO2Saved = perfIndicatorRepository.save(perfIndicator1SO2);
    //            PerfIndicator perfIndicator2SO2Saved = perfIndicatorRepository.save(perfIndicator2SO2);
    //            perfIndicatorRepository.save(perfIndicator3SO2);
    //            Semester semester4Saved = semesterRepository.save(semester4);
    //            Semester semester5Saved = semesterRepository.save(semester5);
    //            course1.setSemester(semester4Saved);
    //            course2.setSemester(semester5Saved);
    //            course1.setAcadProgCurriculums(List.of(acadProgCurriculumSaved));
    //            course2.setAcadProgCurriculums(List.of(acadProgCurriculumSaved));
    //            Course capstoneProjectI = courseRepository.save(course2);
    //            Course internetComputingI = courseRepository.save(course1);
    //            AssessmentType formativeAssessmentTypeSaved = assessmentTypeRepository.save(formativeAssessmentType);
    //            PiLvlCateg introduceSaved = piLvlCategRepository.save(introduce);
    //            PiLvlCateg teachSaved = piLvlCategRepository.save(teach);
    //            PiLvlCateg applySaved = piLvlCategRepository.save(apply);
    //            CurrMap currMap1 = CurrMap.builder()
    //                    .course(internetComputingI)
    //                    .piLvlCateg(introduceSaved)
    //                    .perfIndicator(perfIndicator2SO1Saved)
    //                    .acadProgCurriculum(acadProgCurriculumSaved)
    //                    .cmAcceptedDate(new Date())
    //                    .build();
    //
    //            CurrMap oldCurrMap1 = CurrMap.builder()
    //                    .course(internetComputingI)
    //                    .piLvlCateg(teachSaved)
    //                    .perfIndicator(perfIndicator2SO1Saved)
    //                    .acadProgCurriculum(acadProgCurriculumSaved)
    //                    .cmAcceptedDate(new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L))
    //                    .build();
    //
    //            CurrMap currMap2 = CurrMap.builder()
    //                    .course(internetComputingI)
    //                    .piLvlCateg(teachSaved)
    //                    .perfIndicator(perfIndicator1SO2Saved)
    //                    .acadProgCurriculum(acadProgCurriculumSaved)
    //                    .cmAcceptedDate(new Date())
    //                    .build();
    //
    //            CurrMap currMap3 = CurrMap.builder()
    //                    .course(capstoneProjectI)
    //                    .piLvlCateg(applySaved)
    //                    .perfIndicator(perfIndicator2SO1Saved)
    //                    .acadProgCurriculum(acadProgCurriculumSaved)
    //                    .cmAcceptedDate(new Date())
    //                    .build();
    //
    //            CurrMap currMap4 = CurrMap.builder()
    //                    .course(capstoneProjectI)
    //                    .piLvlCateg(teachSaved)
    //                    .perfIndicator(perfIndicator1SO2Saved)
    //                    .assessmentType(formativeAssessmentTypeSaved)
    //                    .acadProgCurriculum(acadProgCurriculumSaved)
    //                    .cmAcceptedDate(new Date())
    //                    .build();
    //
    //            CurrMap currMap5 = CurrMap.builder()
    //                    .course(capstoneProjectI)
    //                    .piLvlCateg(introduceSaved)
    //                    .perfIndicator(perfIndicator2SO2Saved)
    //                    .acadProgCurriculum(acadProgCurriculumSaved)
    //                    .cmAcceptedDate(new Date())
    //                    .build();
    //
    //            currMapRepository.save(currMap1);
    //            currMapRepository.save(oldCurrMap1);
    //            currMapRepository.save(currMap2);
    //            currMapRepository.save(currMap3);
    //            currMapRepository.save(currMap4);
    //            currMapRepository.save(currMap5);
              };
          }
}