package Services;

import java.io.IOException;

public interface LogInService {
    boolean logIn(String gmailCheck,String password) throws IOException, ClassNotFoundException;
}
