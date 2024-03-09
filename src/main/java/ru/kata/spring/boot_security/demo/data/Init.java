package ru.kata.spring.boot_security.demo.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public Init(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        Set<Role> role = new HashSet<>();
        role.add(roleAdmin);
        role.add(roleUser);
        User admin = new User("admin", "Igor", "Elesin", "igor@mail.ru",
                "100", 27, role, true);

        System.out.println(role);
        System.out.println(admin);

        User user = new User("user", "Anton", "Nazarov", "anton@mail.ru",
                "100", 25, role, false);

        userService.saveUser(admin, roleAdmin.toString());
        userService.saveUser(user, roleUser.toString());
    }
}
