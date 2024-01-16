package pl.tomwodz.numbergenerator.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class WinningNumbersDto {

    private Set<Integer> winningNumbers;
    private LocalDateTime date;

}
