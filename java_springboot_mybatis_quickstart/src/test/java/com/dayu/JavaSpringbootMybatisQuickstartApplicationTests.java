package com.dayu;

import com.dayu.mapper.UserMapper;
import com.dayu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest // 当前测试类中的测试方法运行时，会启动springBoot项目，IOC容器就创建好了 -bean
class JavaSpringbootMybatisQuickstartApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testFindAll() {
		List<User> userList = userMapper.findAll();
		userList.forEach(System.out::println);
	}

	@Test
	public void testDeleteById() {
		Integer i = userMapper.deleteById(1);
		System.out.println("执行完毕影响的记录数："+i);
	}

	@Test
	public void testInsert() {
		User user = new User(1, "admin", "123456", "管理员", 18);
		userMapper.insert(user);
	}

	@Test
	public void testUpdate() {
		User user = new User(1, "updata", "123456", "更新", 18);
		userMapper.update(user);
	}

	@Test
	public void testFindByUsernameAndPassword() {
		User user = userMapper.findByUsernameAndPassword("updata", "123456");
		System.out.println(user);
	}
}
