package pl.tomwodz.drawdategenerator.domain;

import pl.tomwodz.drawdategenerator.domain.dto.DrawDateResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

@Component
@AllArgsConstructor
public class DrawDateGeneratorFacade {
    private final Clock clock;

    private static final LocalTime DRAW_TIME = LocalTime.of(12, 0, 0);
    private static final TemporalAdjuster NEXT_DRAW_DAY = TemporalAdjusters.next(DayOfWeek.SATURDAY);


    public LocalDateTime getNextDrawDate() {
        LocalDateTime currentDateTime = LocalDateTime.now(clock);
        if (isSaturdayAndBeforeNoon(currentDateTime)) {
            return LocalDateTime.of(currentDateTime.toLocalDate(), DRAW_TIME);
        }
        LocalDateTime drawDate = currentDateTime.with(NEXT_DRAW_DAY);
        return LocalDateTime.of(drawDate.toLocalDate(), DRAW_TIME);
    }

    public DrawDateResponseDto getNextDrawDateDto(){
        var nextDrawDate = this.getNextDrawDate();
        return DrawDateResponseDto
                .builder()
                .dateDraw(nextDrawDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    private boolean isSaturdayAndBeforeNoon(LocalDateTime currentDateTime) {
        return currentDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) && currentDateTime.toLocalTime().isBefore(DRAW_TIME);
    }

}
