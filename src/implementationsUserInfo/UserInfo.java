package implementationsUserInfo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserInfo implements Serializable {
    private String fullName;
    private String emailAddress;
    private String passWord;
    private final LocalDateTime registeredDate;


    public UserInfo(String fullName, String emailAddress, String passWord, LocalDateTime registeredDate) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.passWord = passWord;
        this.registeredDate = registeredDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "implementations.UserInfo{" +
                "fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", passWord='" + passWord + '\'' +
                ", registeredDate=" + registeredDate +
                '}';
    }
}
