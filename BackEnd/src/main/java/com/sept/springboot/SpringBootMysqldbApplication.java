
package com.sept.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.sept.springboot.model.Users;
import com.sept.springboot.repository.UsersRepository;
//@EnableJpaRepositories(basePackages = "com.techprimers.db.repository")
@EnableJpaRepositories(basePackages = "com.sept.springboot.repository")
@SpringBootApplication
public class SpringBootMysqldbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMysqldbApplication.class, args);

	}
    @Autowired
    UsersRepository userRepository;
}