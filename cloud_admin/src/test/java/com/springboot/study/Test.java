package com.springboot.study;

import java.io.File;
import java.io.FilenameFilter;

/***
 * Created by IntelliJ IDEA.
 *@author :Administratior
 *@date:2017/11/23
 *To change this template use File|Settings|File Templates.
 */
public class Test {
    public static void main(String[] args) {
        test03("C:\\Users\\Administratior\\Pictures");
    }


    public static void test03(String dir){
        File file = new File(dir);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        test03(file2.getAbsolutePath());
                    } else {
                        if((file2.getName().endsWith(".jpg") || file2.getName().endsWith(".png"))){
                            System.out.println(file2.getName());
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

}
