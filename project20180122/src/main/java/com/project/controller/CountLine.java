package com.project.controller;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CountLine
 * @Description: 我这个类专门针对我这边的项目，如果需要统计自行更改
 * @Author: Looveh
 * @CreateDate: 2018/1/26 15:08
 * @Version: v1.0
 */
public class CountLine {

    private static Integer COUNT_LINE = 0;

    public static void main(String[] args) {

        String rootPath = "E:\\caishang\\";
        //获取文件集合
        List<File> fileList = getFiles(rootPath);
//        System.out.println(fileList.size());
        int countLine = 0;
        for (int i = 0; i < fileList.size(); i++) {
            System.out.println(fileList.get(i).getName());
            //统计集合里面所有文件非空的行数
            countLine += countLine(fileList.get(i));
        }

        System.out.println(countLine);
    }

    /**
     * @方法说明
     * @method getFiles
     * @author hp
     * @date 2018/1/26 16:43
     * @param path
     * @return java.util.List<java.io.File>
     * @desc [获取path下面所有CS开头，.Java结尾或者Mapper.xml结尾的文件]
     */
    public static List<File> getFiles(String path){

        File root = new File(path);
        List<File> fileList = new ArrayList<>();
        //不是文件夹
        if(!root.isDirectory()){
            String fileName = root.getName();
            int sufIndex = fileName.lastIndexOf(".");
            //CS开头并且后缀为.Java或者以Mapper.xml结尾
            //注意：如果项目中没有其他项目的文件，可以不加判断，直接统计所有文件，自行判断
            if(sufIndex != -1 && fileName.startsWith("CS") && (".java".equals(fileName.substring(sufIndex)) || fileName.endsWith("Mapper.xml"))){
                fileList.add(root);
            }
        }else{
            //文件夹使用递归
            File[] subFile = root.listFiles();
            for (File file : subFile){
                if(!file.getName().contains("idea") && !file.getName().contains("job") && !file.getName().contains("svn")){
                    fileList.addAll(getFiles(file.getAbsolutePath()));
                }
            }
        }

        return fileList;
    }

    /**
     * @方法说明
     * @method countLine
     * @author hp
     * @date 2018/1/26 16:07
     * @return int
     * @desc [统计行数]
     */
    public static int countLine(File file){
        //空行数
        int spaceLine = 0;
        //文件行数
        int line = 0;
        InputStreamReader reader = null;
        try {
            //创建一个输入流对象
            reader = new InputStreamReader(new FileInputStream(file));
            //创建一个对象转将文件转换成计算机识别的文件
            BufferedReader br = new BufferedReader(reader);
            String str = br.readLine();
            line+=1;
            while (!StringUtils.isEmpty(str) || spaceLine < 10){
                str = br.readLine();
                //如果这一行为空行，空行+1并跳过这一行
                if(StringUtils.isEmpty(str)){
                    spaceLine+=1;
                    continue;
                }
                //如果空行数达到10行默认算该文件已经读完【可以自行设置】
                if(spaceLine == 10){
                    break;
                }else{
                    line+=1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return line;
    }
}
