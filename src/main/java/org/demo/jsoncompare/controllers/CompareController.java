package org.demo.jsoncompare.controllers;


import org.demo.jsoncompare.logger.LogExecutionTime;
import org.demo.jsoncompare.service.CompareService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/compare")
public class CompareController {

    @Resource
    @Qualifier("compareService")
    CompareService compareService;

    @LogExecutionTime
    @GetMapping(path="/",produces = "application/json")
    public String compare(){
        return compareService.compareDummy("","");
    }

    @LogExecutionTime
    @GetMapping(path="/expected",produces = "application/json")
    public String getExpected() throws Exception{
        return  compareService.getDummyExpected();
    }

    @LogExecutionTime
    @GetMapping(path="/actual",produces = "application/json")
    public String getActual() throws Exception{
        return  compareService.getDummyActual();
    }

    @LogExecutionTime
    @RequestMapping(value = "/fileUploads", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String compareFiles(@RequestParam("expectedJsonUpload") MultipartFile expectedJsonUpload,
                                                 @RequestParam("actualJsonUpload") MultipartFile actualJsonUpload) throws IOException {

        //to do Validations
        ByteArrayInputStream stream = new ByteArrayInputStream(expectedJsonUpload.getBytes());
        String expectedJson = IOUtils.toString(stream, "UTF-8");

        stream = new ByteArrayInputStream(actualJsonUpload.getBytes());
        String actualJson = IOUtils.toString(stream, "UTF-8");

        return compareService.compareJsons(expectedJson,actualJson);

    }

    @LogExecutionTime
    @RequestMapping(value = "/content", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String compareContent(@RequestParam("expectedJsonContent") String expectedJsonContent,
                             @RequestParam("actualJsonContent") String actualJsonContent) throws IOException {

        //to do Validations
        return compareService.compareJsons(expectedJsonContent,actualJsonContent);

    }


}
