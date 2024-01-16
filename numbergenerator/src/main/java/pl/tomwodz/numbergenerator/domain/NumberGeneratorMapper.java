package pl.tomwodz.numbergenerator.domain;

import pl.tomwodz.numbergenerator.domain.dto.WinningNumbersDto;

class NumberGeneratorMapper {

    public  static WinningNumbersDto mapFromWinningNumbersToWinningNumbersDto(WinningNumbers winningNumbers){
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbers.getWinningNumbers())
                .date(winningNumbers.getDate())
                .build();
    }
}
