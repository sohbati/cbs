package com.sina.cbs.document.scenario.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Log4j2
public class ScenarioService {
    private final ScenarioAndComponentLoader scenarioLoader;

    public ScenarioService(ScenarioAndComponentLoader scenarioLoader) {
        this.scenarioLoader = scenarioLoader;
    }

    @Async
    public void sendScenariosToPanelViaWebsocket() {
        try {
            scenarioLoader.sendScenarioListToPanelViaWebsocket();
            scenarioLoader.sendComponentsListToPanelViaWebsocket();
        } catch (IOException e) {
            log.error("Error in loading files");
            log.error(e.getMessage(), e);
        }
    }
}
