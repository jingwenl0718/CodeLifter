package com.jingwenli.codelifter.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("src/main/resources/static/interviewpost-image", registry);
        exposeDirectory("src/main/resources/static/lifestylepost-image", registry);
        exposeDirectory("src/main/resources/static/jobpost-image", registry);
        exposeDirectory("src/main/resources/static/successstory-image", registry);
    }
     
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String[] folderNameSplit = dirName.split("/");
        String folderName = folderNameSplit[folderNameSplit.length - 1];
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
        registry.addResourceHandler("/" + folderName + "/**").addResourceLocations("file://"+ uploadPath + "/");
    }
}
