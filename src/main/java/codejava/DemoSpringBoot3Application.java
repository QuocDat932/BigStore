package codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import codejava.Entity.userObj;

@SpringBootApplication
public class DemoSpringBoot3Application {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBoot3Application.class, args);
	}
	public void run(String... args) throws Exception{
		String sql = "select * from system.sys_users";
		List<userObj> list = 
					jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(userObj.class));
		
		list.forEach(System.out :: println);
	}
}
