package pl.tomwodz.drawdategenerator.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Clock;

@Configuration
public class ClockConfiguration {

    @Bean
    @Primary
    Clock clock() {
        return Clock.systemUTC();
    }

}
