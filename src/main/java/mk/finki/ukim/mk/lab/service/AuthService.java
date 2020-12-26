package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;

public interface AuthService {
    User login(String username,String password);
    User register(String name, String surname, String username, String password, String repeatPassword, Role role);
}
