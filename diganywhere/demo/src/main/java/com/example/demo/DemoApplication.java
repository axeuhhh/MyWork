//package com.example.demo;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import com.example.demo.controller.service.Assistant;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.Scanner;
//
//@SpringBootApplication
//public class DemoApplication {
//
//
//	@Autowired
//	private Assistant assistant;
//
//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) {
//
//		Scanner scanner = new Scanner(System.in);
//		while(true){
//			System.out.print("Enter your question: ");
//			String question = scanner.nextLine();
//
//			int userId = 123; // You can set this to any identifier you prefer
//
//			String response = assistant.chat(userId, question);
//
//			System.out.println("Assistant's response: " + response);
//		}
//	}
//}
package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.controller.service.Assistant;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication /*implements CommandLineRunner*/ {

	@Autowired
	private Assistant assistant;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Override
//	public void run(String... args) {
//		Scanner scanner = new Scanner(System.in);
//		while (true) {
//			System.out.print("Enter your question: ");
//			String question = scanner.nextLine();
//
//			int userId = 123; // You can set this to any identifier you prefer
//
//			String response = assistant.chat(userId, question);
//
//			System.out.println("Assistant's response: " + response);
//		}
//	}
}
