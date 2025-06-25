package com.compass.challange.voting.system.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaInput {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

}
