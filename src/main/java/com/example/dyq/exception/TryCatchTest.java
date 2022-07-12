package com.example.dyq.exception;

/**
 * @author dongyuqiang
 * @date 2022/02/08 11:31
 **/
public class TryCatchTest {


    public static int test(){
        int x=1;
        try{
            return x;
        }finally{
            x=2;
        }
    }
}