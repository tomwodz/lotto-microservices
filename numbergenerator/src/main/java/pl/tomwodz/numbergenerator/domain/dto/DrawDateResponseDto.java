package pl.tomwodz.numbergenerator.domain.dto;

import lombok.Builder;

@Builder
public record DrawDateResponseDto(
        String dateDraw
) {
}
