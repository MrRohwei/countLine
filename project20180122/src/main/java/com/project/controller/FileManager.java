package com.project.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: FileManager
 * @Description: 一句话描述该类的功能
 * @Author: Looveh
 * @CreateDate: 2018/1/26 19:54
 * @Version: v1.0
 */
public class FileManager {

    public static void main(String[] args) {

        try {
            File[] root = getSystemDriver();
            Map map = dir(new File("D:\\www"));
            System.out.println(map);
//            for (int i = 0; i < list.size(); i++) {
////                Map current_file = list.get(i);
//                if (current_file.isDirectory()){
//                    System.out.println("文件夹：" + current_file.getName());
//                    System.out.println("文件夹绝对路径："+current_file.getAbsolutePath());
//                }else{
//                    File parent_file = current_file.getParentFile();
//                    System.out.println("文件：" + current_file.getName()+"我的父文件夹："+parent_file.getName());
//                    System.out.println("文件绝对路径："+current_file.getAbsolutePath());
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @方法说明
     * @method getSystemDriver
     * @author Looveh
     * @date 2018/1/26 20:02
     * @param
     * @return java.io.File[]
     * @desc [获取系统盘符]
     */
    public static File[] getSystemDriver() throws Exception {
        //盘符
//        List<Map> rootFile = new ArrayList<>();
        File[] root = File.listRoots();
        if(null != root && root.length > 0){
            return root;
        }else{
            throw new Exception("Not find drive");
        }
    }

    /*public static Map getAllFileByDriver(File file) throws Exception {
        if(null == file){
            throw new Exception("parameter[file] can not be null");
        }

        Map dirs = new HashMap<>();

        File[] fileList = file.listFiles();
        List<File> files = new ArrayList<>();
        String dirName = "";
        for (int i = 0; i < fileList.length; i++) {
            if(fileList[i].isDirectory()) {
                dirName = fileList[i].getName();
                files.addAll(getAllFileByDriver(fileList[i]));
            }else{
                files.add(fileList[i]);
            }
        }
        map.put(dirName,files);
        list.add(map);

        *//*for (File subFile : fileList){
//            if("www".equals(subFile.getName())) {
                if(subFile.isDirectory()){
                    directory+=subFile;//文件夹放入集合
                    list.addAll(getAllFileByDriver(subFile));
//                }
            }else{
                file1.add(subFile.getName());
            }
        }*//*
//
//        for (int i = 0; i < directory.size(); i++) {
//            System.out.println(directory.get(i));
//        }
        return list;
    }*/

    /**
     * @方法说明
     * @method dir
     * @author Looveh
     * @date 2018/1/26 20:56
     * @param file
     * @return void
     * @desc [获取文件夹下面的文件夹]
     */
    public static Map dir(File file) throws Exception {
        if(null == file){
            throw new Exception("parameter[file] can not be null");
        }
        File[] dirs = file.listFiles();
        Map result = new HashMap<>();
        List<File> files =null;
        for (File dir: dirs) {
            if(dir.isDirectory()){
                result.put(dir.getName(),files(dir));
                continue;
            }else{
                files = new ArrayList<>();
                files.add(dir);
            }
            result.put(dir.getParentFile().getName(),files);
        }
        return result;
    }

    /**
     * @方法说明
     * @method files
     * @author Looveh
     * @date 2018/1/26 21:07
     * @param file
     * @return java.util.List<java.io.File>
     * @desc [获取文件夹下面所有的文件]
     */
    public static List<File> files(File file) throws Exception {
        if(null == file){
            throw new Exception("parameter[file] can not be null");
        }

        File[] files = file.listFiles();
        List<File> list = new ArrayList<>();
        //假如该目录下只有文件，没有文件夹
        for (File file1: files) {
            if(file1.isFile()){
                list.add(file1);
            }else{
                dir(file);
            }
        }

        return list;
    }
}
