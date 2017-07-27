package com.nowcoder.weibo.dao;

import com.nowcoder.weibo.model.User;
import org.apache.ibatis.annotations.*;
import org.hibernate.validator.constraints.Email;

/**
 * Created by soyo pu on 2017/7/25.
 */
@Mapper
public interface UserDAO {
    //@Insert({"insert into user(name,password,salt,head_url) values()"})
    String TABLE_NAME=" user ";
    String INSERT_FIELDS=" name,email,password,salt,head_url ";
    String SELECT_FIELDS=" id, "+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (#{name},#{email},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where name=#{name}"})
    User selectByName(String name);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id=#{id}"})
    User selectById(int id);
    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where email=#{email}"})
    User selectByEmail(String email);

    void updatePassword(User user);
}


