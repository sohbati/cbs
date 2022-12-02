package com.sina.cbs.document.scenario.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class ScenarioAndComponentLoader {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public ScenarioAndComponentLoader(SimpMessagingTemplate simpMessagingTemplate) {

        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendScenarioListToPanelViaWebsocket() throws IOException {
       collectFilesAndSend("scenarios", "scenario");
    }

    public void sendComponentsListToPanelViaWebsocket() {
        collectFilesAndSend("components", "components");
    }

    private void collectFilesAndSend(String folder, String topic) {
        // read all files from a resources folder
        try {
            // files from /resources/scenarios
            List<File> result = getAllFilesFromResource(folder);
            for (File file : result) {
                log.info(MessageFormat.format("file : {0}", file));
                String jsonStr = getStringContent(file);
                log.info(jsonStr);
                simpMessagingTemplate.convertAndSend("/topic/" + topic, jsonStr);
            }
        } catch (URISyntaxException | IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    
    private List<File> getAllFilesFromResource(String folder) throws URISyntaxException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(folder);
        // dun walk the root path, we will walk all the classes
        List<File> collect = Files.walk(Paths.get(resource.toURI()))
                .filter(Files::isRegularFile)
                .map(x -> x.toFile())
            .collect(Collectors.toList());
        return collect;
    }

    // print a file
    private  String getStringContent(File file) throws IOException {
         return Files.readString(file.toPath()).replaceAll(
                 "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","");
    }
}
