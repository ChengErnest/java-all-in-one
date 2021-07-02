package cn.nicollcheng.btrace.entity;

public class User {
    private int id;
    private String name;
    public User(int id, String name) {
        super();  // 必须加上super,不然无法拦截到参数
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public User setId(int id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public User setName(String name) {
        this.name = name;
        return this;
    }
}