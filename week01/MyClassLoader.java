package com.jyz.train;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jiangyouzhi on 2021/6/22.
 * *.xlass 文件放在windows D盘下
 */
public class MyClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "d:\\"+name+".xlass";
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            Files.copy(Paths.get(path),os);
            //得到字节数组
            byte[] bytes = os.toByteArray();
            byte[] reverseByte = new byte[bytes.length];
            //255-bytes[i]  题目中所有字节(x=255-x),这里也用255-bytes[i]，得到原来的字节
            for(int i = 0; i < bytes.length; i++){
                reverseByte[i]= (byte)(255-bytes[i]);
            }
            return defineClass(name,reverseByte,0,reverseByte.length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException("类文件异常");
        }finally {
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException{
        MyClassLoader classLoder = new MyClassLoader();
        Class class1 = classLoder.loadClass("Hello");
        System.out.println("Class:\t\t"+class1);
        Method[] methods = class1.getDeclaredMethods();
        //System.out.println(class1 +"methods:"+methods.length);
        //System.out.println(class1 +"method:"+methods[0]);
        try {
            Object obj = class1.newInstance();
            try {
                System.out.print("类方法调用：");
                methods[0].invoke(obj);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
