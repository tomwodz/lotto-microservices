package pl.tomwodz.drawdategenerator.domain.dto;

import lombok.Builder;

@Builder
public record DrawDateResponseDto(
        String dateDraw
) {
}
