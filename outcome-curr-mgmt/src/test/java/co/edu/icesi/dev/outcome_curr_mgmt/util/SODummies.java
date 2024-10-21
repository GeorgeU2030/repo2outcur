package co.edu.icesi.dev.outcome_curr_mgmt.util;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.PerfIndicator;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;

import java.util.ArrayList;
import java.util.List;

public class SODummies {

    public static final char YES = 'Y';
    private static final String LONG_NAME_ENG_SO1 = "SO1 - Problem solving";
    private static final String LONG_NAME_SPA_SO1 = "SO1 - Solución de problemas: Identificar, formular y resolver problemas complejos de ingeniería aplicando pensamiento crítico y principios de las ciencias, las matemáticas, la ingeniería y, en particular, de las Ciencias de la Computación y de la Ingeniería de Software.";
    private static final String SHORT_NAME_ENG_SO1 = "SO1 - Problem solving";
    private static final String SHORT_NAME_SPA_SO1 = "SO1 - Solución de problemas";
    private static final String LONG_NAME_ENG_SO2 = "SO2 - Design";
    private static final String LONG_NAME_SPA_SO2 = "SO2 – Diseño de Ingeniería: Diseñar soluciones y procesos basados en software que satisfagan necesidades específicas y generen valor a sus usuarios, considerando la salud pública, la seguridad y el bienestar de las personas, así como factores globales, culturales, sociales, ambientales y económicos.";
    private static final String SHORT_NAME_ENG_SO2 = "SO2 - Design";
    private static final String SHORT_NAME_SPA_SO2 = "SO2 – Diseño de Ingeniería";
    private static final String PI_SHORT_NAME_ENG_PI1_SO1 = "1-PI1 Problem identification and formulation";
    private static final String PI_SHORT_NAME_SPA_PI1_SO1 = "SO1-PI1 Identificación y formulación de problemas";
    private static final String PI_LONG_NAME_ENG_PI1_SO1 = "1-PI1 Problem identification and formulation: to identify a complex problem and formulate it, by applying principles of engineering (the engineering method).";
    private static final String PI_LONG_NAME_SPA_PI1_SO1 = "SO1-PI1 Identificación y formulación de problemas: Identificar un problema complejo y formularlo, aplicando principios de ingeniería y el método de ingeniería.";
    private static final String PI_SHORT_NAME_ENG_PI2_SO1 = "1-PI2 Problem solution (engineering/science)";
    private static final String PI_SHORT_NAME_SPA_PI2_SO1 = "SO1-PI2 Solución de problema (ingeniería/ciencias)";
    private static final String PI_LONG_NAME_ENG_PI2_SO1 = "1-PI2 Problem solution (engineering/science): to solve complex problems by proposing a strategy compatible with the formulation and by applying principles of engineering or science.";
    private static final String PI_LONG_NAME_SPA_PI2_SO1 = "SO1-PI2 Solución de problema (ingeniería/ciencias): Resolver problemas complejos proponiendo una estrategia compatible con la formulación y aplicando principios de ingeniería o ciencias.";
    private static final String PI_SHORT_NAME_ENG_PI3_SO1 = "1-PI3 Problem solution (mathematics)";
    private static final String PI_SHORT_NAME_SPA_PI3_SO1 = "SO1-PI3 Solución del problema (matemáticas)";
    private static final String PI_LONG_NAME_ENG_PI3_SO1 = "1-PI3 Problem solution (mathematics): to solve complex problems by proposing strategies compatible with their formulation and by applying mathematics.";
    private static final String PI_LONG_NAME_SPA_PI3_SO1 = "SO1-PI3 Solución del problema (matemáticas): Resolver problemas complejos proponiendo estrategias compatibles con su formulación y aplicando matemáticas.";
    private static final String PI_SHORT_NAME_ENG_PI1_SO2 = "2-PI1 Detailed Design";
    private static final String PI_SHORT_NAME_SPA_PI1_SO2 = "SO2-PI1 Diseño detallado";
    private static final String PI_LONG_NAME_ENG_PI1_SO2 = "2-PI1 Detailed Design: To produce detailed design specifications that meet functional requirements, correctly using the corresponding tools and notations.";
    private static final String PI_LONG_NAME_SPA_PI1_SO2 = "SO2-PI1 Diseño detallado: Producir especificaciones de diseño detalladas que cumplan con los requerimientos funcionales, utilizando correctamente las herramientas y notaciones correspondientes.";
    private static final String PI_SHORT_NAME_ENG_PI2_SO2 = "2-PI2 High Level Design";
    private static final String PI_SHORT_NAME_SPA_PI2_SO2 = "SO2-PI2 Diseño de alto nivel";
    private static final String PI_LONG_NAME_ENG_PI2_SO2 = "2-PI2 High Level Design: To produce architectural specifications, by correctly using the corresponding tools and notations, that meet functional and non-functional requirements, as well as consider public health, safety, welfare, as well as global, cultural, social, environmental, and economic factors, when relevant.";
    private static final String PI_LONG_NAME_SPA_PI2_SO2 = "SO2-PI2 Diseño de alto nivel: Producir especificaciones arquitectónicas mediante el uso correcto de las herramientas y notaciones correspondientes, que cumplan con los requerimientos funcionales y no funcionales, además de considerar la salud pública, la seguridad, el bienestar, así como los factores globales, culturales, sociales, ambientales y económicos, cuando sea relevante.";
    private static final String PI_SHORT_NAME_ENG_PI3_SO2 = "2-PI3 Evaluation of solution design";
    private static final String PI_SHORT_NAME_SPA_PI3_SO2 = "SO2-PI3 Evaluación de diseño de soluciones";
    private static final String PI_LONG_NAME_ENG_PI3_SO2 = "2-PI3 Evaluation of solution design: To evaluate architectural specifications with respect to specified needs and most significant concerns (among non-functional requirements and aspects such as public health, safety, welfare, as well as global, cultural, social, environmental, and economic factors).";
    private static final String PI_LONG_NAME_SPA_PI3_SO2 = "SO2-PI3 Evaluación de diseño de soluciones: Evaluar las especificaciones arquitectónicas con respecto a las necesidades específicas y las preocupaciones más importantes (entre los requerimientos no funcionales y aspectos como la salud pública, la seguridad, el bienestar, así como los factores globales, culturales, sociales, ambientales y económicos).";

    public static List<StudOutcome> getStudentOutcomesDummies() {
        List<StudOutcome> studOutcomes = new ArrayList<>();
        StudOutcome studOutcome1 = StudOutcome.builder()
                .soId(1L)
                .soIsActive(YES)
                .soLongNameEng(LONG_NAME_ENG_SO1)
                .soLongNameSpa(LONG_NAME_SPA_SO1)
                .soShortNameEng(SHORT_NAME_ENG_SO1)
                .soShortNameSpa(SHORT_NAME_SPA_SO1)
                .soOrdinalNumber(1)
                .build();

        StudOutcome studOutcome2 = StudOutcome.builder()
                .soId(2L)
                .soIsActive(YES)
                .soLongNameEng(LONG_NAME_ENG_SO2)
                .soLongNameSpa(LONG_NAME_SPA_SO2)
                .soShortNameEng(SHORT_NAME_ENG_SO2)
                .soShortNameSpa(SHORT_NAME_SPA_SO2)
                .soOrdinalNumber(2)
                .build();

        PerfIndicator perfIndicator1SO1 = PerfIndicator.builder()
                .piId(1L)
                .piShortNameEng(PI_SHORT_NAME_ENG_PI1_SO1)
                .piShortNameSpa(PI_SHORT_NAME_SPA_PI1_SO1)
                .piLongNameEng(PI_LONG_NAME_ENG_PI1_SO1)
                .piLongNameSpa(PI_LONG_NAME_SPA_PI1_SO1)
                .studOutcome(studOutcome1)
                .piOrdinalNumber(1)
                .build();

        PerfIndicator perfIndicator2SO1 = PerfIndicator.builder()
                .piId(2L)
                .piShortNameEng(PI_SHORT_NAME_ENG_PI2_SO1)
                .piShortNameSpa(PI_SHORT_NAME_SPA_PI2_SO1)
                .piLongNameEng(PI_LONG_NAME_ENG_PI2_SO1)
                .piLongNameSpa(PI_LONG_NAME_SPA_PI2_SO1)
                .studOutcome(studOutcome1)
                .piOrdinalNumber(2)
                .build();

        PerfIndicator perfIndicator3SO1 = PerfIndicator.builder()
                .piId(3L)
                .piShortNameEng(PI_SHORT_NAME_ENG_PI3_SO1)
                .piShortNameSpa(PI_SHORT_NAME_SPA_PI3_SO1)
                .piLongNameEng(PI_LONG_NAME_ENG_PI3_SO1)
                .piLongNameSpa(PI_LONG_NAME_SPA_PI3_SO1)
                .studOutcome(studOutcome1)
                .piOrdinalNumber(3)
                .build();

        PerfIndicator perfIndicator1SO2 = PerfIndicator.builder()
                .piId(4L)
                .piShortNameEng(PI_SHORT_NAME_ENG_PI1_SO2)
                .piShortNameSpa(PI_SHORT_NAME_SPA_PI1_SO2)
                .piLongNameEng(PI_LONG_NAME_ENG_PI1_SO2)
                .piLongNameSpa(PI_LONG_NAME_SPA_PI1_SO2)
                .studOutcome(studOutcome2)
                .piOrdinalNumber(1)
                .build();

        PerfIndicator perfIndicator2SO2 = PerfIndicator.builder()
                .piId(5L)
                .piShortNameEng(PI_SHORT_NAME_ENG_PI2_SO2)
                .piShortNameSpa(PI_SHORT_NAME_SPA_PI2_SO2)
                .piLongNameEng(PI_LONG_NAME_ENG_PI2_SO2)
                .piLongNameSpa(PI_LONG_NAME_SPA_PI2_SO2)
                .studOutcome(studOutcome2)
                .piOrdinalNumber(2)
                .build();

        PerfIndicator perfIndicator3SO2 = PerfIndicator.builder()
                .piId(6L)
                .piShortNameEng(PI_SHORT_NAME_ENG_PI3_SO2)
                .piShortNameSpa(PI_SHORT_NAME_SPA_PI3_SO2)
                .piLongNameEng(PI_LONG_NAME_ENG_PI3_SO2)
                .piLongNameSpa(PI_LONG_NAME_SPA_PI3_SO2)
                .studOutcome(studOutcome2)
                .piOrdinalNumber(3)
                .build();

        List<PerfIndicator> perfIndicatorsSO1 = new ArrayList<>();
        perfIndicatorsSO1.add(perfIndicator1SO1);
        perfIndicatorsSO1.add(perfIndicator2SO1);
        perfIndicatorsSO1.add(perfIndicator3SO1);

        studOutcome1.setPerfIndicators(perfIndicatorsSO1);

        List<PerfIndicator> perfIndicatorsSO2 = new ArrayList<>();
        perfIndicatorsSO2.add(perfIndicator1SO2);
        perfIndicatorsSO2.add(perfIndicator2SO2);
        perfIndicatorsSO2.add(perfIndicator3SO2);

        studOutcome2.setPerfIndicators(perfIndicatorsSO2);

        studOutcomes.add(studOutcome1);
        studOutcomes.add(studOutcome2);

        return studOutcomes;
    }
}
