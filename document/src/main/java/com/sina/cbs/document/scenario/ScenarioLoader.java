package com.sina.cbs.document.scenario;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.stereotype.Component;

@Component
public class ScenarioLoader {

    public void loadJsonFiles() throws IOException {
        // read all files from a resources folder
        try {
            // files from src/main/resources/json
            List<File> result = getAllFilesFromResource("scenarios");
            for (File file : result) {
                System.out.println("file : " + file);
                String jsonStr = getStringContent(file);
                System.out.println(jsonStr);
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
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
         return Files.readString(file.toPath());
    }
}
