package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.model.exceptions.UserAlreadyExistsException;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void checkUsernameAndPassword(String username,String password){
        if(username==null||username.isEmpty()||password==null||password.isEmpty()){
            throw new InvalidArgumentsException();
        }
    }

    @Override
    public User login(String username, String password) {
        checkUsernameAndPassword(username,password);
        return userRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public User register(String name, String surname, String username, String password, String repeatPassword) {
        checkUsernameAndPassword(username,password);
        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }
        if(userRepository.findByUsername(username).isPresent()){
            throw new UserAlreadyExistsException(username);
        }
        return userRepository.save(new User(name,surname,username,password));
    }
}
