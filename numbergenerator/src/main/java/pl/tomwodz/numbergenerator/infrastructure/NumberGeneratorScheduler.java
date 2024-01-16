package pl.tomwodz.numbergenerator.infrastructure;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.tomwodz.numbergenerator.domain.NumberGeneratorFacade;
import pl.tomwodz.numbergenerator.domain.dto.WinningNumbersDto;

@Component
@AllArgsConstructor
@Log4j2
public class NumberGeneratorScheduler {

    private final NumberGeneratorFacade numberGeneratorFacade;

    @Scheduled(cron = "0/10 * * * * *")
    public void generateWinningNumbers(){
        log.info("Winning numbers scheduler started.");
        WinningNumbersDto winningNumbersDto = numberGeneratorFacade.generateWinningNumbers();
        log.info(winningNumbersDto.getWinningNumbers());
        log.info(winningNumbersDto.getDate());
    }
}
