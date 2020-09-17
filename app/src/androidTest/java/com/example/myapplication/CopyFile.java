package com.example.myapplication;

import java.io.*;
import java.util.ArrayList;

/**
 * 合并文件并输出
 */
public class CopyFile {

    public static void main(String[] args) throws Exception {
        copyAll();
    }

    static int pageSize = 43;// 一页行数

    private static void copyAll() throws Exception {
        //读取文件路径
        String sourcePath = "/Users/zhoucong/Desktop/Code/MyApplication/app/src/main/java/com/example";
//        String sourcePath = "/Users/qingbo/Downloads/aa/LMAPP_Android-dev/app/src/main/assets/detecting";
//        String sourcePath = "/Users/qingbo/Desktop/de";
        //读取文件后缀名
        String suffix = ".java";
        String charsetName = "UTF-8";
        String pathStr = "/Users/zhoucong/Desktop/";
        String fileName = "code.txt";
        File filePath = new File(pathStr);
        if (!filePath.exists()) {
            filePath.mkdirs();
        } else {
            filePath.delete();
            filePath.mkdirs();
        }
        String destination = pathStr + fileName;

        //存储文件路径，true 代表  可追加
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destination, true));
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destination, true), charsetName));
        //统计复制文件数量
        int fileCount = 0;
        int lineCount = 0;
        //true 获取存储文件目录下所有存在文件的文件夹全路径，false  获取存储文件路径下所有文件全路径（true的子文件）,
        ArrayList<String> path = getDirPath(new File[]{new File(sourcePath)}, false, suffix);
        System.out.println("copy starting......");
        //遍历路径，进行复制
        for (String txtName : path) {
            //读取路径文件，"utf-8"防止中文乱码
            BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(txtName), charsetName));
            //按行读取
            String str = null;
            while ((str = buf.readLine()) != null) {

                //写入数据
                String data = str.trim();
//                if (data.contains("先导压力")||data.contains("泵1主压;Pump1Press;")) {
                    //写入数据
                    bufferedWriter.append(data);
                    //换行
                    bufferedWriter.newLine();
                    lineCount++;
                    if (lineCount / pageSize >= 350) {
                        break;
                    }
//                }

            }
            System.out.println("copy \"" + new File(txtName).getName() + "\" successed!");
            fileCount++;
            buf.close();
        }
        bufferedWriter.close();
        System.out.println("copy ending......");
        System.out.println("FilesCount==" + fileCount);
        System.out.println("lineCount==" + lineCount);
    }

    //获取最后一层文件夹,sign  true获取最后一层文件夹，false 获取所有文件
    public static ArrayList<String> getDirPath(File[] files, Boolean sign, String suffix) {
        ArrayList<String> fs = new ArrayList<String>();
        for (File f : files) {
            if (f.isDirectory())
                fs.addAll(getDirPath(f.listFiles(), sign, suffix));
            else {
                if (sign) {
                    if (!fs.contains(f.getParent()))
                        if (endWithCh(f, suffix))
                            fs.add(f.getParent());
                } else {
                    if (!fs.contains(f.getPath()))
                        if (endWithCh(f, suffix))
                            fs.add(f.getPath());
                }

            }
        }
        return fs;
    }

    //以。。。结尾,"",或者null,默认无后缀名
    public static Boolean endWithCh(File file, String suffix) {
        if (suffix == "" || suffix == null) return true;
        if (file.getName().endsWith(suffix))
            return true;
        else
            return false;

    }
}
