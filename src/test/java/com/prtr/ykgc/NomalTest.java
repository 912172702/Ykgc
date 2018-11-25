package com.prtr.ykgc;

import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @Author: Knox
 * @Date: 2018/11/25 8:16 PM
 * @Description: You Know
 * @Version 1.0
 */
public class NomalTest {

    @Test
    public void test() {
        List<String> a = new ArrayList<>();
        System.out.println(ObjectUtils.isEmpty(a));
    }

    @Test
    public void test2() {
       // System.out.println(Arrays.asList(IntStream.range(0, 8).boxed().map(i->i)).get(6));
    }

}
