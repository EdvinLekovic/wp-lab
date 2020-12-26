package mk.finki.ukim.mk.lab;


import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.finki.ukim.mk.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.model.exceptions.UserAlreadyExistsException;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import mk.finki.ukim.mk.lab.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        User user = new User("username","password","name","surname", Role.ROLE_ADMIN);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.userService = Mockito.spy(new UserServiceImpl(userRepository,passwordEncoder));
    }

    @Test
    public void testSuccessRegister(){
        User user = this.userService.register("username","password","password","name","surname",Role.ROLE_ADMIN);

        Mockito.verify(this.userService).register("username","password","password","name","surname",Role.ROLE_ADMIN);

        Assert.assertNotNull("User is not null",user);

        Assert.assertEquals("username do not match","username",user.getUsername());
        Assert.assertEquals("password do not match","password",user.getPassword());
        Assert.assertEquals("name do not match","name",user.getName());
        Assert.assertEquals("surname do not match","surname",user.getSurname());
        Assert.assertEquals("Role do not match",Role.ROLE_ADMIN,user.getRole());
    }

    @Test
    public void testNullUsername(){
        Assert.assertThrows("InvalidUsernameOrPasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> userService.register(null,"password","password","name","surname",Role.ROLE_ADMIN));
        Mockito.verify(this.userService).register(null,"password","password","name","surname",Role.ROLE_ADMIN);
    }


    @Test
    public void testEmptyUsername(){
        Assert.assertThrows("InvalidUsernameOrPasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> userService.register("","password","password","name","surname",Role.ROLE_ADMIN));
        Mockito.verify(this.userService).register("","password","password","name","surname",Role.ROLE_ADMIN);
    }

    @Test
    public void testNullPassword(){
        Assert.assertThrows("InvalidUSernameOrpasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> userService.register("username",null,"password","name","surname",Role.ROLE_ADMIN));
        Mockito.verify(this.userService).register("username",null,"password","name","surname",Role.ROLE_ADMIN);
    }

    @Test
    public void testEmptyPassword(){
        Assert.assertThrows("InvalidUSernameOrpasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> userService.register("username","","password","name","surname",Role.ROLE_ADMIN));
        Mockito.verify(this.userService).register("username","","password","name","surname",Role.ROLE_ADMIN);
    }

    @Test
    public void testPasswordMismatch(){
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> userService.register("username","password","repeatPassword","name","surname",Role.ROLE_ADMIN));
        Mockito.verify(userService).register("username","password","repeatPassword","name","surname",Role.ROLE_ADMIN);
    }

    @Test
    public void testDuplicateUsername(){
        User user = new User("username","password","name","surname", Role.ROLE_ADMIN);
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UserAlreadyExistsException.class,
                () -> userService.register("username","password","password","name","surname",Role.ROLE_ADMIN));
        Mockito.verify(userService).register("username","password","password","name","surname",Role.ROLE_ADMIN);
    }



}
