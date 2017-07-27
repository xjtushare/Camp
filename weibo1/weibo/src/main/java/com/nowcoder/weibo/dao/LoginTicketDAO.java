package com.nowcoder.weibo.dao;


import com.nowcoder.weibo.model.LoginTicket;
import com.nowcoder.weibo.model.User;
import org.apache.ibatis.annotations.*;
import sun.security.krb5.internal.Ticket;

/**
 * Created by soyo pu on 2017/7/26.
 */
@Mapper
public interface LoginTicketDAO {
    String TABLE_NAME=" login_ticket ";
    String INSERT_FIELDS=" user_id,expired,status,ticket ";
    String SELECT_FIELDS=" id, "+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values" +
            " (#{user_id},#{expired},#{status},#{ticket})"})
    int addTicket(LoginTicket ticket);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where ticket=#{ticket}"})
    LoginTicket selectByTicket(String ticket);

    //更新状态，是在用户登出了以后。
    @Update({"update",SELECT_FIELDS,"from",TABLE_NAME," status=#{status} where ticket=#{ticket}"})
    void UpdateStatus(@Param("ticket")String ticket, @Param("status") int status);

}


