package top.chaoranlaoge.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassloader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(this.getClass().getClassLoader().getResource("Hello.xlass").getFile());
        long fileSize = file.length();
        byte[] buffer = new byte[(int)fileSize];
        try(FileInputStream fi = new FileInputStream(file)) {
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset != buffer.length) {
                System.out.println("error");
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        for(int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) (255 - buffer[i]);
        }
        return defineClass(name, buffer, 0, buffer.length);
    }

    public static void main(String[] args) {
        try {
            CustomClassloader classloader = new CustomClassloader();
            Class c = classloader.findClass("Hello");
            Method m = c.getMethod("hello");
            m.invoke(c.newInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
