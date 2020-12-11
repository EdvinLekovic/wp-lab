package mk.finki.ukim.mk.lab.model.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String username){
        super(String.format("User with username %s already exist in the system",username));
    }
}
