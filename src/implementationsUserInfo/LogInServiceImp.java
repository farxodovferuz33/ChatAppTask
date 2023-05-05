package implementationsUserInfo;

import Services.LogInService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LogInServiceImp implements LogInService {
    private UserInfo o;
    @Override
    public boolean logIn(String gmailCheck, String password) throws IOException, ClassNotFoundException {
        String pathname = "TXTFiles\\" + gmailCheck + ".txt";
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pathname));
        o = (UserInfo) objectInputStream.readObject();
        if (gmailCheck.equals(o.getEmailAddress()) && password.equals(o.getPassWord()))
            return true;
        else return false;
    }

    public UserInfo getO() {
        return o;
    }
}
