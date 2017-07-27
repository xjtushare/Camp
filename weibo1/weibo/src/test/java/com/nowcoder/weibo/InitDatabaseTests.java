//package com.nowcoder.weibo;
//
//import com.nowcoder.weibo.dao.UserDAO;
//import com.nowcoder.weibo.model.LoginTicket;
//import com.nowcoder.weibo.model.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Random;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
////@Sql("/init-schema.sql	")
//public class InitDatabaseTests {
//	@Autowired
//	UserDAO userDAO;
//
////测试，创建10个用户，设置字段
//	@Test
//	public void initData() {
//		//添加随机值
//		Random random = new Random();
//		for(int i=0;i<11;i++){
//			 User user = new User();
//			 //设置字段
//			//设置头像
//			user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
//			//设置名字
//			user.setName(String.format("USER%d",i));
//			user.setPassword("");
//			user.setSalt("	");
//			//设置完之后，把user加入进去
//			userDAO.addUser(user);
//
//			//修改密码
//			user.setPassword("newpassword");
//			userDAO.updatePassword(user);
//
//
//
//			//测试Ticket
//
//			LoginTicket ticket = new LoginTicket();
//			ticket.setStatus(0);
//
//
//
//
//
//
//
//
//		}
//		//测试Assert:   获取新密码
//		Assert.assertEquals("newpassword", userDAO.selectById(1).getPassword());
//		userDAO.deleteById(1);//删掉1  //注意UserDAO是类，如果用类来调用就是调用静态方法
//		//对象调用的是动态方法
//		Assert.assertNull(userDAO.selectById(1));//再取出为null
//	}
//
//}
