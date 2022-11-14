package com.sina.cbs.document.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sina.cbs.document.scenario.ScenarioLoader;

@RestController
public class DocumentApi {

    @Autowired
    ScenarioLoader scenarioLoader;

    @RequestMapping("/test")
    public String test() {
        try {
            scenarioLoader.loadJsonFiles();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "{'tr' : 'ff'}";
    }
}
