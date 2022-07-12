package com.example.dyq.javaguide.java8;

import com.example.dyq.dto.Person;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/9/12 10:08
 * @Description:
 */
public class StreamDemo {

    public static void main(String[] args) {

//        testOne();

//        testTwo();
//
//        testThree();
//         testFour();
        Person person = null;
        Optional.ofNullable(person).ifPresent(f->{
            f.setAdmin(true);
            f.setEmail("11111");
            System.out.println("数据入库");
        });
        System.out.println("其他业务处理");
    }

    public static void testOne() {

        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
        System.out.println(concat);

    }


    public static void testTwo(){
        /**
         * 案例1：统计 List 中的单词长度大于6的个数
         */
        ArrayList<String> wordsList = new ArrayList<String>();
        wordsList.add("Charles");
        wordsList.add("Vincent");
        wordsList.add("William");
        wordsList.add("Joseph");
        wordsList.add("Henry");
        wordsList.add("Bill");
        wordsList.add("Joan");
        wordsList.add("Linda");
        wordsList.add("Linda");
        int count = 0;

        //正常遍历
        for(String s:wordsList){
            if(s.length()>6){
                count++;
            }
        }
        System.out.println("for循环遍历过滤统计:"+count);
        //Stream 遍历
        long count1 = wordsList.stream().filter(w -> w.length() > 6).count();
        System.out.println("stream过滤统计"+count1);
        long count2 = wordsList.parallelStream().filter(w -> w.length() > 6).count();
        System.out.println("stream并行过滤统计"+count2);

        List<String> collect = wordsList.stream().distinct().collect(Collectors.toList());
        System.out.println("去重之后的list:");
        collect.stream().forEach(s->System.out.print(" "+s));
        System.out.println();

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取 List 中每个元素对应的平方数并去重
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.println("获取 List 中每个元素对应的平方数并去重: "+squaresList.toString());//[9, 4, 49, 25]
        List<Integer> collect1 = Stream.of(1, 2, 3, 4, 5).skip(2).collect(Collectors.toList());
        collect1.stream().forEach(s->System.out.print(" "+s));
        System.out.println();
        List<Integer> collect2 = wordsList.stream().map(String::length).collect(Collectors.toList());
        collect2.stream().forEach(s->System.out.print(" "+s));
    }

    //将以下字符串按逗号分割成List,去除每个成员项首位空格，过滤空白项，合并重复项
    //，逆序排列。（优先使用JDK8新特性）
    public static void testThree(){
        String s = " z111,c888,n222,,,g000, t333,a999,c888 ,p000 ,z111 ";
        String[] split = s.split("\\,");
        List<String> strings = Arrays.asList(split);
        strings.stream().map(x->x.trim()).filter(x->!x.isEmpty()).distinct().forEach(System.out::println);

        List<String> wordList = Arrays.asList("Hello", "World");
        List<String> strList = wordList.stream()
                .map(w -> w.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        strList.stream().forEach(System.out::print);

    }

    public static  void testFour(){
        String abandonResultCompany="1,2,3,4,5";
        Long companyId=3L;
        boolean b = Optional.ofNullable(abandonResultCompany)
                .filter(f -> !StringUtils.isEmpty(f))
                .map(f -> f.split(","))
                .map(Arrays::asList)
                .orElse(Collections.emptyList())
                .stream().map(Long::valueOf)
                .anyMatch(f -> f.equals(companyId));
        System.out.println(b);

    }

}

