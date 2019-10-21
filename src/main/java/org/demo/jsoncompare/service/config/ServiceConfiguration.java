package org.demo.jsoncompare.service.config;

import org.demo.jsoncompare.service.CompareService;
import org.demo.jsoncompare.service.CompareServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean("compareService")
    public CompareService compareService(@Qualifier("fileUtils") FileUtils fileUtils,
                                         @Qualifier("objectMapper") ObjectMapper objectMapper){
        return new CompareServiceImpl(fileUtils,objectMapper);
    }

    @Bean("fileUtils")
    public FileUtils fileUtils(){
        return new FileUtils();
    }

    @Bean("objectMapper")
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
