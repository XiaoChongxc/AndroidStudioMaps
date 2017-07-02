package com.example.admin_xc.basemodule.util;

import java.math.BigDecimal;

/**
 * User:    Xiaoc
 * 项目名:  WZB3.0
 * Package: baizhuan.hangzhou.com.wzb30.util
 * Date:    2016-09-10
 * Time:    14:35
 * 类描述： 数字 计算 ， 避免 double  float  计算的时候 的 一些精度问题
 */
public class ArithUtil {
        private static final int DEF_DIV_SCALE=10;

        private ArithUtil(){}

    /**
     * 加法
     * @param d1
     * @param d2
     * @return
     */
        public static double add(double d1,double d2){
            BigDecimal b1=new BigDecimal(Double.toString(d1));
            BigDecimal b2=new BigDecimal(Double.toString(d2));
            return b1.add(b2).doubleValue();

        }

    /**
     * 减法
     * @param d1
     * @param d2
     * @return
     */
        public static double sub(double d1,double d2){
            BigDecimal b1=new BigDecimal(Double.toString(d1));
            BigDecimal b2=new BigDecimal(Double.toString(d2));
            return b1.subtract(b2).doubleValue();

        }

    /**
     * 乘法
     * @param d1
     * @param d2
     * @return
     */
        public static double mul(double d1,double d2){
            BigDecimal b1=new BigDecimal(Double.toString(d1));
            BigDecimal b2=new BigDecimal(Double.toString(d2));
            return b1.multiply(b2).doubleValue();

        }

    /***
     * 除法
     * @param d1
     * @param d2
     * @return
     */
        public static double div(double d1,double d2){

            return div(d1,d2,DEF_DIV_SCALE);

        }

    /***
     * 除法
     * @param d1
     * @param d2
     * @param scale
     * @return
     */
        public static double div(double d1,double d2,int scale){
            if(scale<0){
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
            BigDecimal b1=new BigDecimal(Double.toString(d1));
            BigDecimal b2=new BigDecimal(Double.toString(d2));
            return b1.divide(b2,scale, BigDecimal.ROUND_HALF_UP).doubleValue();

        }

}
