package com.algorithm.operator;

import lombok.extern.slf4j.Slf4j;

/**
 * 异或运算
 */
@Slf4j
public class XOR {

    public static void main(String[] args) {
        // test01(new int[]{1, 2, 2, 3, 3, 4, 4, 5});
        test02(new int[]{1, 2, 2, 3, 3, 4, 4, 5});
    }

    /**
     * 题目：【找数组中出现奇数次的数】（一种出现了奇数次）
     * 原理：简单的全部抑或，偶数的抑或就为0，剩下一个奇数的数
     */
    public static void test01(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        log.info("找到只出现奇数次的数值={}", eor);
    }

    /**
     * 题目：【找数组中出现奇数次的数】（两种出现了奇数次）
     * 原理：
     * 1、简单的全部异或，偶数的异或就为0，剩下两个奇数次数的数的异或eor=a^b;
     * 2、将a，b分组：因为eor=a^b;所以eor中为1的位置，必为a和b不一样的位置，由此可以给a，b分组，不妨每次我们都寻找最右边的一位，并记录下结果rightone。
     * 找到eor里最右边一位为1的数，例如9（1001）和3（0011）抑或为10（1010），寻找最右边一位1结果就是2（0010）。此时数组就被分为：某一位为1的和某一位不为1的，a，b分别在不同的组。
     * 3、最后，只需要将上述分组的某一组拉出来全抑或（得到了eorr），便可回归1.2.1问题；另一个数仅需要将eor和eorr抑或便可得到：因为a^a^b=b;b^a^b=a;
     */
    public static void test02(int[] arr) {
        int eor = 0;
        //[1]结果为a^b(ab为不同的两数)
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        //[2]取出eor最右边的1
        int rightone = eor & (~eor + 1);
        int eorr = 0;
        for (int i = 0; i < arr.length; i++) {
            //[3]取出arr中与eor在相同位置为1的数
            if ((rightone & arr[i]) == rightone) {
                eorr ^= arr[i];//满足条件继续全部抑或
            }
        }
        int first = eorr;
        int second = eor ^ eorr;
        log.info("第一个奇数次数的数为={}", first);
        log.info("第二个奇数次数的数为={}", second);
    }


}
