package pl.tomwodz.numbergenerator.infrastructure.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import pl.tomwodz.numbergenerator.domain.dto.DrawDateResponseDto;

@FeignClient(value = "drawdategenerator-service")
@Component
public interface DrawDateGeneratorProxy {
    @GetMapping(value = "/drawdategenerator")
    DrawDateResponseDto getNextDrawDate();
}
