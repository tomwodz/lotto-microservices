package pl.tomwodz.numbergenerator.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tomwodz.numbergenerator.infrastructure.proxy.DrawDateGeneratorProxy;


@Configuration
class NumberGeneratorConfiguration {

    @Bean
    public NumberGeneratorFacade numberGeneratorFacade(WinningNumbersRepository winningNumbersRepository,
                                                       DrawDateGeneratorProxy drawDateGeneratorProxy
    ) {return new NumberGeneratorFacade(winningNumbersRepository, drawDateGeneratorProxy);
    }
}
