package pl.tomwodz.numbergenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.tomwodz.numbergenerator.domain.dto.CriteriaForGenerateNumbersConfigurationProperties;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@EnableConfigurationProperties({
        CriteriaForGenerateNumbersConfigurationProperties.class})
public class NumberGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(NumberGeneratorApplication.class, args);
    }
}
