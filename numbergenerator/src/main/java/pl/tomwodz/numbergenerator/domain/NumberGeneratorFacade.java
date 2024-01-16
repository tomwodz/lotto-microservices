package pl.tomwodz.numbergenerator.domain;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.tomwodz.numbergenerator.domain.dto.CriteriaForGenerateNumbersConfigurationProperties;
import pl.tomwodz.numbergenerator.domain.dto.DrawDateResponseDto;
import pl.tomwodz.numbergenerator.domain.dto.WinningNumbersDto;
import pl.tomwodz.numbergenerator.infrastructure.proxy.DrawDateGeneratorProxy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@AllArgsConstructor
@Log4j2
public class NumberGeneratorFacade {

    private final WinningNumbersRepository winningNumbersRepository;
    private final NumberRandomGeneratorRepository numberGeneratorRepository;
    private final DrawDateGeneratorProxy drawDateGeneratorClient;
    private final WinningNumbersFactory winningNumbersFactory;
    private final CriteriaForGenerateNumbersConfigurationProperties criteria;

    public WinningNumbersDto generateWinningNumbers() {
        LocalDateTime nextDrawDate = getNextDrawDate();
        //TODO connect to service
        Set<Integer> winningGenerateNumbers = numberGeneratorRepository.generateSixRandomNumbers(criteria);
        //TODO add new class / validatorFacade.validationWinningNumbers(winningGenerateNumbers);
        WinningNumbers winningNumbers = winningNumbersFactory
                .mapFromWinningNumbersDtoToWinningNumbers(winningGenerateNumbers, nextDrawDate);
        WinningNumbers winningNumbersSaved = saveWinningNumber(winningNumbers);
        return NumberGeneratorMapper.mapFromWinningNumbersToWinningNumbersDto(winningNumbersSaved);
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
            return LocalDateTime.parse(drawDateResponseDto.dateDraw(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (FeignException.FeignClientException exception){
            log.warn("drawDateGeneratorClient error: " + exception.getMessage());
        }
        return LocalDateTime.now(); //TODO
    }

}
