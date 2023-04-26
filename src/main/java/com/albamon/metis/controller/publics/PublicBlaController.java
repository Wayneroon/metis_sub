package com.albamon.metis.controller.publics;

import com.albamon.constants.Version;
import com.albamon.metis.dto.bla.BlaConfigurationDto;
import com.albamon.metis.service.PublicBlaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/public/" + Version.V1 + "/bla")
@RestController
public class PublicBlaController {

    private final PublicBlaService publicBlaService;

    @PostMapping("/deploy")
    public boolean saveConfigurationContents(@RequestBody @Valid BlaConfigurationDto request) {
        publicBlaService.saveConfigurationContents(request.getFileName(), request.getPath(), request.getDescVal());
        return true;
    }
}
