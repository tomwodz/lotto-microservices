package pl.tomwodz.numbergenerator.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.numbergenerator.domain.dto.CriteriaForGenerateNumbersConfigurationProperties;
import pl.tomwodz.numbergenerator.infrastructure.proxy.DrawDateGeneratorProxy;


@Configuration
class NumberGeneratorConfiguration {

    @Bean
    public NumberGeneratorFacade numberGeneratorFacade(WinningNumbersRepository winningNumbersRepository,
                                                       NumberRandomGeneratorRepository numberRandomGeneratorRepository,
                                                       DrawDateGeneratorProxy drawDateGeneratorProxy,
                                                       CriteriaForGenerateNumbersConfigurationProperties criteria
    ) {
        WinningNumbersFactory winningNumbersFactory = new WinningNumbersFactory();
        return new NumberGeneratorFacade(winningNumbersRepository,
                numberRandomGeneratorRepository,
                drawDateGeneratorProxy,
                winningNumbersFactory,
                criteria);
    }
}
