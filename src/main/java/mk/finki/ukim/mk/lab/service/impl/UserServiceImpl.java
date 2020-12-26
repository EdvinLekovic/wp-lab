package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.finki.ukim.mk.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.model.exceptions.UserAlreadyExistsException;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {

        if(username==null||username.isEmpty()||password==null||password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }

        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }
        if (userRepository.findByUsername(username).isPresent()){
            throw new UserAlreadyExistsException(username);
        }
        return userRepository.save(new User(username,passwordEncoder.encode(password),name,surname,role));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return  userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }
}
