package jiyun.com.oschinawork.http;

import com.thoughtworks.xstream.XStream;

/**
 * Created by Administrator on 2017/4/12.
 */

public class TextStream<T> {
    private XStream xStream  = new XStream();
    private TextStream(){
    }
    private static  TextStream xStreamDemo;
    public  static synchronized  TextStream getInstance(){
        if(xStreamDemo==null){
            xStreamDemo = new TextStream();
        }
        return xStreamDemo;
    }
    public TextStream alies(String type,Class classes){
        xStream.alias(type,classes);
        return xStreamDemo;
    }
    public T build(String str,Class classes){
        T t = null;
        try {
            t = (T) classes.newInstance();
            t = (T) xStream.fromXML(str);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
