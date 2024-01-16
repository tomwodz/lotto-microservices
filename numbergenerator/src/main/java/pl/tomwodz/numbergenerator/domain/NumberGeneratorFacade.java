package pl.tomwodz.numbergenerator.domain;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.tomwodz.numbergenerator.domain.dto.DrawDateResponseDto;
import pl.tomwodz.numbergenerator.domain.dto.WinningNumbersDto;
import pl.tomwodz.numbergenerator.infrastructure.proxy.DrawDateGeneratorProxy;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Log4j2
public class NumberGeneratorFacade {

    private final WinningNumbersRepository winningNumbersRepository;
    private final DrawDateGeneratorProxy drawDateGeneratorClient;

    public WinningNumbersDto generateWinningNumbers() {
        getNextDrawDate();
        return NumberGeneratorMapper.mapFromWinningNumbersToWinningNumbersDto(
                new WinningNumbers(Set.of(1,2,3,4,5,6), LocalDateTime.now())); //TODO
    }

    private WinningNumbers saveWinningNumber(WinningNumbers winningNumbers){
        if(!this.winningNumbersRepository.existsByDate(winningNumbers.getDate())){
            return winningNumbersRepository.save(winningNumbers);
        }
        log.error("DrawDate is already exists in database.");
        return winningNumbersRepository.findWinningNumbersByDate(winningNumbers.getDate())
                .orElseThrow(() -> new WinningNumbersNotFoundException("Not Found"));
    }

    public WinningNumbersDto retrieveWinningNumberByDate(LocalDateTime date) {
        WinningNumbers numbersByDate = winningNumbersRepository.findWinningNumbersByDate(date)
                .orElseThrow(() -> new WinningNumbersNotFoundException("Not Found"));
        return NumberGeneratorMapper.mapFromWinningNumbersToWinningNumbersDto(numbersByDate);
    }

    public boolean areWinningNumbersGeneratedByDate() {
        LocalDateTime nextDrawDate = getNextDrawDate();
        return winningNumbersRepository.existsByDate(nextDrawDate);
    }

    private LocalDateTime getNextDrawDate(){
        try {
            DrawDateResponseDto drawDateResponseDto = drawDateGeneratorClient.getNextDrawDate();
            log.info(drawDateResponseDto.dateDraw());
        } catch (FeignException.FeignClientException exception){
            log.warn("drawDateGeneratorClient error: " + exception.getMessage());
        }
        return LocalDateTime.now();
    }

}
