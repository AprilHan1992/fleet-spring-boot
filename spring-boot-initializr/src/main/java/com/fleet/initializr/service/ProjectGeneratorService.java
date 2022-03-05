package com.fleet.initializr.service;

import com.fleet.initializr.entity.Application;

/**
 * @author April Han
 */
public interface ProjectGeneratorService {

    void generator(Application application) throws Exception;
}
