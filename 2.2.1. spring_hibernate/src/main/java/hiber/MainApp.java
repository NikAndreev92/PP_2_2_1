package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);


       UserService userService = context.getBean(UserService.class);
       CarService carService = context.getBean(CarService.class);

       User user1 = new User("User1","User1", "user1@mail");
       userService.add(user1);
       carService.addCar(new Car("model1",1,user1));

       List<User> users = userService.listUsers();
       for (User user : users) {
           System.out.println("Id = " + user.getId());
           System.out.println("First Name = " + user.getFirstName());
           System.out.println("Last Name = " + user.getLastName());
           System.out.println("Email = " + user.getEmail());
           System.out.println();

       }
       System.out.println("Владелец model1 " + carService.getUserByCar("model1", 1).toString());
       System.out.println();


      context.close();
   }
}
