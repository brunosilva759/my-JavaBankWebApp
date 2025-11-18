package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.persistence.TransactionManager;
import io.codeforall.bootcamp.javabank.persistence.dao.jpa.JpaAccountDao;
import io.codeforall.bootcamp.javabank.persistence.dao.jpa.JpaCustomerDao;
import io.codeforall.bootcamp.javabank.persistence.jpa.JpaSessionManager;
import io.codeforall.bootcamp.javabank.persistence.jpa.JpaTransactionManager;
import io.codeforall.bootcamp.javabank.services.AccountServiceImpl;
import io.codeforall.bootcamp.javabank.services.AuthServiceImpl;
import io.codeforall.bootcamp.javabank.services.CustomerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {


        App app = new App();

        ConfigurableApplicationContext configurableApplicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

        Controller controller = configurableApplicationContext.getBean("loginController", Controller.class);

        controller.init();

        configurableApplicationContext.close();



    }

}
