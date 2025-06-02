package com.project.recipe.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public class ResponseDto {
    private final HttpStatus status;
    private final String message;
}
