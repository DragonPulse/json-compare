package org.demo.jsoncompare.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CompareServiceImpl implements CompareService {

    FileUtils fileUtils;
    ObjectMapper jacksonObjectMapper ;

    public CompareServiceImpl(FileUtils fileUtils,ObjectMapper objectMapper) {
        this.fileUtils = fileUtils;
        this.jacksonObjectMapper=objectMapper;
    }

    @Override
    public String compareDummy(String source, String dest) {

//        File expectedJsonFile = new File(
//                getClass().getClassLoader().getResource(RQA2_JSON_PATH).getFile()
//        );
//        File actualJsonFile = new File(
//                getClass().getClassLoader().getResource(RQA3_JSON_PATH).getFile()
//        );


        String diff="";
        try {
            String expectedJson = getDummyExpected();//FileUtils.readFileToString(expectedJsonFile, Charset.defaultCharset());
            String actualJson = getDummyActual();//FileUtils.readFileToString(actualJsonFile,Charset.defaultCharset());
            ObjectMapper jacksonObjectMapper = new ObjectMapper();
            JsonNode patch = getJsonDiff(expectedJson, actualJson, jacksonObjectMapper);
            diff=patch.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diff;
    }

    @Override
    public String compareJsons(String source, String dest) {

        try {
            JsonNode patch = getJsonDiff(source, dest, jacksonObjectMapper);
            return patch.toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JsonNode getJsonDiff(String source, String dest, ObjectMapper jacksonObjectMapper) throws JsonProcessingException {
        JsonNode beforeNode = jacksonObjectMapper.readTree(source);
        JsonNode afterNode = jacksonObjectMapper.readTree(dest);
        return JsonDiff.asJson(beforeNode, afterNode);
    }

    @Override
    public String getDummyExpected()  {
        try{

            String strCurrentLine;
            InputStream in = getClass().getResourceAsStream("/sample1.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            while ((strCurrentLine = reader.readLine()) != null) {
                sb.append(strCurrentLine);
                sb.append(System.getProperty("line.separator"));
            }
//            return FileUtils.readFileToString( new File(
//                    getClass().getClassLoader().getResource(RQA2_JSON_PATH).getFile()
//            ), Charset.defaultCharset());
            return sb.toString();
        }catch(Exception e){

        }
        return "";

    }

    @Override
    public String getDummyActual() {
        try{
            String strCurrentLine;
            InputStream in = getClass().getResourceAsStream("/sample2.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            while ((strCurrentLine = reader.readLine()) != null) {
                sb.append(strCurrentLine);
                sb.append(System.getProperty("line.separator"));
            }
//            return FileUtils.readFileToString( new File(
//                    getClass().getClassLoader().getResource(RQA3_JSON_PATH).getFile()
//            ), Charset.defaultCharset());
            return sb.toString();
        }catch(Exception e){

        }
        return "";
    }
}
