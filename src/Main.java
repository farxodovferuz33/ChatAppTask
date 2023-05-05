import implementationsUserInfo.LogInServiceImp;
import implementationsUserInfo.SenderServiceImpl;
import implementationsUserInfo.UserInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static int checkFirstStep = 0;
    static Scanner scanner = new Scanner(System.in);
    static String gmail = null;
    static String name;
    static String password;
    static Logger logger = Logger.getLogger("MyLog");

    public static void main(String[] args) {
        System.out.println("1 ---> to Sign up (Authorization)");
        System.out.println("2 ---> to Log in");
        System.out.println("Please enter integer");
        checkFirstStep = scanner.nextInt();

        switch (checkFirstStep) {
            case 1 -> {
                System.out.println("----> Authorization <----");
                System.out.println("Enter you name");
                name = scanner.next();
                System.out.println("Enter your gmail");
                gmail = scanner.next();
                System.out.println("Enter you password");
                password = scanner.next();
                try {
                    if (gmailTesterRegex(gmail)) {
                        String pathname = "TXTFiles\\" + gmail + ".txt";
                        File checkUserToAuth = new File(pathname);
                        if (!checkUserToAuth.exists()) {
                            System.out.println("----> REGISTERED <----");
                            logger.info("✅ User is registered email: " + gmail);
                            UserInfo user = new UserInfo(name, gmail, password, LocalDateTime.now());
                            File file = new File("TXTFiles\\" + user.getEmailAddress() + ".txt");
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            writeObjInTxt(user, fileOutputStream);
                            System.out.println(user);
                            userInBoxAndOutBox(user);
                        } else System.err.println("User is already exist");
                    } else System.out.println("Error please fill input correct gmail (@gmail.)");
                } catch (RuntimeException e) {
                    logger.log(Level.SEVERE, "Email is not correct format", e);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
            case 2 -> {
                System.out.println("----> LOG IN <----");
                System.out.println("Enter your gmail");
                gmail = scanner.next();
                System.out.println("Enter your password");
                password = scanner.next();
                try {
                    String pathname = "TXTFiles\\" + gmail + ".txt";
                    File checkUserToLogInFile = new File(pathname);
                    if (gmailTesterRegex(gmail)) {
                        if (checkUserToLogInFile.exists()) {
                            LogInServiceImp userLogInInfo = new LogInServiceImp();
                            if (userLogInInfo.logIn(gmail, password)) {
                                System.out.println("----> Found logged in <----");
                                logger.info("✅ User is Logged email: " + gmail);
                                UserInfo user = userLogInInfo.getO();
                                userInBoxAndOutBox(user);
                            } else System.err.println("Password is incorrect");
                        } else System.err.println("User not found");
                    } else System.err.println("Gmail format is not correct");
                } catch (RuntimeException e) {
                    logger.log(Level.SEVERE, "Email is not correct format or gmail not found please register", e);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void writeObjInTxt(Object user, FileOutputStream fileOutputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(user);
    }

    private static void userInBoxAndOutBox(UserInfo user) throws IOException, ClassNotFoundException {
        System.out.println("Welcome, " + user.getFullName());

        while (true) {
            System.out.println("1 ----> To check inbox messages");
            System.out.println("2 ----> To send message");
            int checkInOut = scanner.nextInt();
            switch (checkInOut) {
                case 1 -> {
                    System.out.println("----> INBOX <----");
                    File file = new File("TXTFiles\\Chat.txt");
                    if (file.length() != 0) checkingInboxMessages(user, file);
                    else System.err.println("You do not have any message");
                }
                case 2 -> {
                    System.out.println("Please enter email to send message");
                    String toEmail = scanner.next();
                    System.out.println("Please enter your message");
                    String message = scanner.next();
                    String pathname = "TXTFiles\\" + toEmail + ".txt";
                    File file = new File(pathname);

                    if (gmailTesterRegex(toEmail)) {
                        if (file.exists()) {
                            SenderServiceImpl senderService = new SenderServiceImpl(user.getEmailAddress(), toEmail, message, LocalDateTime.now());
                            if (senderService.sendMessage(senderService.getFrom(), senderService.getTo(), senderService.getMessage(), senderService.getMessageSentTime()))
                                System.out.println("Message is sending\nMessage is sent");
                            else System.err.println("Message cannot be sent. Server Error!");
                        } else System.err.println("User not found");
                    } else
                        System.err.println("Email format is not correct");
                }
            }
            if (checkInOut == 0) {
                System.err.println("Illegal number entered leaving");
                break;
            }
        }
    }

    private static void checkingInboxMessages(UserInfo user, File file) throws IOException {
        Path path = file.toPath();
        List<String> messages = Files.readAllLines(path);
        boolean b = false;
        for (String message : messages) {
            if (message.contains(user.getEmailAddress())) {
                System.out.println(message);
                b=true;
            }
        }
        if (!b){
            System.out.println("You do not have any message");
        }
    }

    private static boolean gmailTesterRegex(String gmail) {
        if (gmail == null) throw new RuntimeException();
        Pattern pattern = Pattern.compile("(\\w+)@([\\w-]+)\\.(\\w{2,4})");
        Matcher matcher = pattern.matcher(gmail);
        return matcher.matches();
    }

}