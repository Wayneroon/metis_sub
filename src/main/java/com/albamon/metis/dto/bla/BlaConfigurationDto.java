package com.albamon.metis.dto.bla;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BlaConfigurationDto {
    @NotBlank
    private String fileName;
    @NotBlank
    private String path;
    @NotBlank
    private String descVal;
}
