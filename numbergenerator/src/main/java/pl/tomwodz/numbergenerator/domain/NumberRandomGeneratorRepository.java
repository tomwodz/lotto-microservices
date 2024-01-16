package pl.tomwodz.numbergenerator.domain;

import pl.tomwodz.numbergenerator.domain.dto.CriteriaForGenerateNumbersConfigurationProperties;

import java.util.Set;

interface NumberRandomGeneratorRepository {

    Set<Integer> generateSixRandomNumbers(CriteriaForGenerateNumbersConfigurationProperties criteria);

}
