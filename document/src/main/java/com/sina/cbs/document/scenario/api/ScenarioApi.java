package com.sina.cbs.document.scenario.api;

import com.sina.cbs.document.scenario.service.ScenarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/scenarios")
public class ScenarioApi {

    private final ScenarioService scenarioService;

    public ScenarioApi(ScenarioService scenarioService) {

        this.scenarioService = scenarioService;
    }

    @GetMapping()
    public void sendScenariosToPanelThroughWebsocket() {
        scenarioService.sendScenariosToPanelViaWebsocket();
    }
}
