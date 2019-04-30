package com.purewhite.ywc.purewhitelibrary.config;

import android.text.TextUtils;

import java.text.DecimalFormat;

public class StringUtils {

    //字符串转换长数字
    public static double stringToDouble(String context)
    {
        if (TextUtils.isEmpty(context))
        {
            return 0;
        }
        else
        {
            double num=0;
            try
            {
                num=Double.parseDouble(context);
            }
            catch (Exception e)
            {

            }
            finally {
                return num;
            }

        }
    }


    //字符串转换长数字
    public static int stringToInt(String context)
    {
        if (TextUtils.isEmpty(context))
        {
            return 0;
        }
        else
        {
            int num=0;
            try
            {
                num=Integer.parseInt(context);
            }
            catch (Exception e)
            {

            }
            finally {
                return num;
            }

        }
    }


    //数字转换成字符串，保留2位小数
    public static String doubleToString2(double num){
        return new DecimalFormat("0.00").format(num);
    }

    //数字转换成字符串，保留一位小数
    public static String doubleToString1(double num){
        return new DecimalFormat("0.0").format(num);
    }



    //获取销量
    public static String obtainSola(String num)
    {
       return obtainSola(stringToInt(num));
    }

    //获取销量
    public static String obtainSola(int num)
    {
        if (num<10000)
        {
            return 9000+"";
        }
        else
        {
            return doubleToString1(num)+"万";
        }
    }



}
