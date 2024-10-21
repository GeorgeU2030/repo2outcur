package co.edu.icesi.dev.outcome_curr_mgmt.util;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.CurrMap;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.PiLvlCateg;

import java.util.ArrayList;
import java.util.List;

public class CurrMapsDummies {

    public static final char YES = 'Y';
    public static final String CATEGORY_NAME_ENG_INTRODUCE = "Introduce";
    public static final String CATEGORY_NAME_SPA_INTRODUCE = "Introducir";
    public static final String CATEGORY_NAME_ENG_TEACH = "Teach";
    public static final String CATEGORY_NAME_SPA_TEACH = "Enseñar";
    public static final String CATEGORY_NAME_ENG_APPLY = "Apply";
    public static final String CATEGORY_NAME_SPA_APPLY = "Aplicar";

    public static List<CurrMap> getCurrMapsDummies() {
        List<CurrMap> currMaps = new ArrayList<>();

        PiLvlCateg introduce = PiLvlCateg.builder()
                .categIsActive(YES)
                .categNameEng(CATEGORY_NAME_ENG_INTRODUCE)
                .categNameSpa(CATEGORY_NAME_SPA_INTRODUCE)
                .categPosition(1)
                .build();

        PiLvlCateg teach = PiLvlCateg.builder()
                .categIsActive(YES)
                .categNameEng(CATEGORY_NAME_ENG_TEACH)
                .categNameSpa(CATEGORY_NAME_SPA_TEACH)
                .categPosition(2)
                .build();

        PiLvlCateg apply = PiLvlCateg.builder()
                .categIsActive(YES)
                .categNameEng(CATEGORY_NAME_ENG_APPLY)
                .categNameSpa(CATEGORY_NAME_SPA_APPLY)
                .categPosition(3)
                .build();

        CurrMap currMap1 = CurrMap.builder()
                .cmId(1L)
                .piLvlCateg(introduce)
                .build();

        CurrMap currMap2 = CurrMap.builder()
                .cmId(2L)
                .piLvlCateg(introduce)
                .build();

        CurrMap currMap3 = CurrMap.builder()
                .cmId(3L)
                .piLvlCateg(teach)
                .build();

        CurrMap currMap4 = CurrMap.builder()
                .cmId(4L)
                .piLvlCateg(teach)
                .build();

        CurrMap currMap5 = CurrMap.builder()
                .cmId(5L)
                .piLvlCateg(apply)
                .build();

        currMaps.add(currMap1);
        currMaps.add(currMap2);
        currMaps.add(currMap3);
        currMaps.add(currMap4);
        currMaps.add(currMap5);

        return currMaps;
    }
}
