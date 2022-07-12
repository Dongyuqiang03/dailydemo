package com.example.dyq;

import com.example.dyq.service.test.PreCheckDecision;
import com.example.dyq.service.test.PreCheckEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DyqApplicationTests {

    @Resource
    private List<PreCheckDecision> preCheckDecisions;

    @Test
    public void contextLoads() {

        PreCheckEntity preCheckEntity = new PreCheckEntity(preCheckDecisions);
        int checkResult = preCheckEntity.getCheckResult();
    }


    public static void main(String[] args) {
        ClassLoader.getSystemClassLoader();
    }
}
