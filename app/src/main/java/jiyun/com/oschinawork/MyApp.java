package jiyun.com.oschinawork;

/**
 * Created by Administrator on 2017/5/9.
 */

public class MyApp {
    private String name;
    private int age;

    public MyApp(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
