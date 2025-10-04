package com.example.Search_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
//		exclude = {
//		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
//}
)
@EnableJpaRepositories("com.example.Search_project.repository")
public class SearchProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchProjectApplication.class, args);
	}

}
