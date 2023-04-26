package com.albamon.metis.service;

import com.albamon.metis.controller.advice.Error;
import com.albamon.metis.exception.BusinessException;
import com.albamon.metis.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublicBlaService extends BaseService {

    public void saveConfigurationContents(String fileName, String path, String descVal) {
        // result file path
        Path filePath = Paths.get(path + File.separator + fileName);

        try (FileWriter fw = new FileWriter(filePath.toString())) {
            fw.write(descVal);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(Error.SERVER_EXCEPTION.getMessage());
        }

        log.debug("BLA Configuration Save Path : {}" , filePath);
    }

}
