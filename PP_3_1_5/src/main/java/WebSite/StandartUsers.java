package WebSite;

import WebSite.model.Role;
import WebSite.model.User;
import WebSite.service.RoleService;
import WebSite.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StandartUsers {

    private final UserService userService;
    private final RoleService roleService;

    public StandartUsers(UserService userRepository, RoleService roleRepository) {
        this.userService = userRepository;
        this.roleService = roleRepository;
    }

    @PostConstruct
    public void createUsers() {

        /** Создание и созранение ролей **/
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        roleService.save(user);
        roleService.save(admin);

        /** Создание пользователей **/

        // Пароль в BCrypt для 1 пользователя $2a$12$OOBMFFxKfPclYE.fK7agveJbAohzJuMj9p1HH58j28HVVdRy22rNS

        User user1 = new User("yashtlsht@mail.ru", "123");
        User user2 = new User("Alex@mail.ru","234");
        User user3 = new User("Irina@mail.ru", "345");
        user1.setFirstName("Дмитрий");
        user1.setLastName("Зинкин");

        user2.setFirstName("Алексей");
        user2.setLastName("Зинкин");

        user3.setFirstName("Ирина");
        user3.setLastName("Ручкина");

        /** Установка им ролей **/
        user1.setRole(roleService.findByName("ROLE_ADMIN"));
        user1.setRole(roleService.findByName("ROLE_USER"));
        user2.setRole(roleService.findByName("ROLE_ADMIN"));
        user3.setRole(roleService.findByName("ROLE_USER"));

        /** Сохранение пользователей **/
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);

    }
}

