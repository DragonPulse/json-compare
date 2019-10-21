package org.demo.jsoncompare.service;



public interface CompareService {

   String compareJsons(String source, String dest);

    String compareDummy(String source, String dest);

    String getDummyExpected();

    String getDummyActual();
}
