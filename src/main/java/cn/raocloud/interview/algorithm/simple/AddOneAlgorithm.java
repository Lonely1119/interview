package cn.raocloud.interview.algorithm.simple;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * 最简单的高频面试题 “加1”
 *   给定一个由整数组成的非空数组所表示的非负整数，在该书的基础上加1
 *   前提条件:
 *      1.最高位数字存在在数组的首位
 *      2.数组中的每个元素只存储单个数字
 *      3.整数不会以0开头
 *   示例:
 *      输入: [1,2,3]
 *      输出: [1,2,4]
 *      解释: 输入数组表示数字123
 *   需考虑两种情况:
 *      1. 普通情况，除9之外的数字加1
 *      2. 特殊情况，9加1
 *         2-1. 边界情况: 数字每位都为9
 *
 */
public class AddOneAlgorithm {

    public static void main(String[] args) {
        System.out.println(algorithm(Arrays.asList(1, 2, 3)));
        System.out.println(algorithm(Arrays.asList(9, 9)));
    }

    public static List<Integer> algorithm(List<Integer> digitList){
        for(int index = digitList.size()-1; 0 <= index; index--){
            if(digitList.get(index) < 9){
                int digit = digitList.get(index) + 1;
                digitList.set(index, digit);
                return digitList;
            } else {
                digitList.set(index, 0);
            }
        }
        List<Integer> tempList = new ArrayList<Integer>(){{ add(1); }};
        tempList.addAll(digitList);
        return tempList;
    }
}
