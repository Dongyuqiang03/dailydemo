package com.example.dyq.file;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTest1 {
    public static void main(String[] args) throws IOException {
        String readFilePath="D:\\pullData\\hefei\\hefei_broker_batch_save_0826_result.txt";
        String writerFilePath="D:\\pullData\\hefei\\hefei_broker_batch_save_0826_result_new1.txt";
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(writerFilePath));

        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(readFilePath));
        String bufferedReaderStr;
        while ((bufferedReaderStr = bufferedReader.readLine()) != null) {
            String[] line = bufferedReaderStr.split("\t");
            String houseCode = line[0];
            try {
                    bufferedWriter.write(houseCode);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (Exception e) {
                }

//            }

        }

    }
}
