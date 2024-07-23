package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User kris = new User("Kris", "Volkova", "kris@mail.ru");
      User maxon = new User("Maxon", "Tuktarev", "maxon@mail.ru");
      User olga = new User("Olga", "Semen", "olga@mail.ru");
      User alex = new User("Alex", "Volkov", "alex@mail.ru");

      Car volvo = new Car("volvo", 20);
      Car granta = new Car("granta", 30);
      Car volga = new Car("volga", 40);
      Car porshe = new Car("porshe", 50);

      kris.setCar(volvo);
      maxon.setCar(granta);
      olga.setCar(porshe);
      alex.setCar(volga);

      userService.add(kris);
      userService.add(maxon);
      userService.add(olga);
      userService.add(alex);
//      userService.add(volvo);
//      userService.add(granta);
//      userService.add(porshe);
//      userService.add(volga);




      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
           }

      List<Car> cars = userService.carList();
      for (Car car : cars) {
         System.out.println("Car = "+car.getModel());
         System.out.println("Car = "+car.getSeries());
      }

      System.out.println("Владелец гранты - это " + userService.getUserByCar("granta", 30));


      context.close();
   }
}

