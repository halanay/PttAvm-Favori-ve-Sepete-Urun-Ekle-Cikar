package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    //1- Create the object of Properties
    private static Properties properties;

    static{

        try {

            //2- We need to open the file in java memory: FileInputStream
            String path = "src/test/resources/configuration.properties";
            FileInputStream file = new FileInputStream(path);
            properties = new Properties();
            //3- Load the properties object using FileInputStream object
            properties.load(file);

            //close the file
            file.close();


        } catch (IOException e) {
            System.out.println("File not found in the ConfigurationReader class.");
            e.printStackTrace();
        }

    }

    public static String getProperty(String keyword){

        return properties.getProperty(keyword);
    }


}