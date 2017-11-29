package com.kaishengit;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Administrator.
 */
public class Test {
    public static void main(String[] args) {
        String P = DigestUtils.md5Hex("123123" + "#&@!,.^*&(*&)(*)");
        System.out.println(P);
    }
}
