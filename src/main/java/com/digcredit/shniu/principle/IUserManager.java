package com.digcredit.shniu.principle;

/**
 * Created by shniu on 2017/3/12.
 */


interface IUserBO {

}

public interface IUserManager {

    // 这个是一个不遵守SRP的列子

    // 这个方法的定义是根据传递的类型不同，把可变长度参数changeOptions修改到userBO对象上
    // 并调用持久层保存到数据库中
    void changeUser(IUserBO userBO, String... changeOptions);

}
