package codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import codejava.Entity.Users;



@SpringBootApplication
public class DemoSpringBoot3Application {
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBoot3Application.class, args);
	}

}
