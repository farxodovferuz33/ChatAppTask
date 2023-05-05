package implementationsUserInfo;

import Services.SenderService;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SenderServiceImpl implements SenderService, Serializable {
    private String from;
    private String to;
    private String message;
    private LocalDateTime messageSentTime;

    @Override
    public boolean sendMessage(String fromEmail, String toEmail, String message, LocalDateTime localDateTime) throws IOException {
        Path of = Path.of("C:\\Users\\Feruz\\IdeaProjects\\ChatApp\\TXTFiles\\Chat.txt");
        String infoChat = "fromUser: %s, toUser: %s, message: %s time: %s".formatted(fromEmail, toEmail, message, localDateTime);
        Files.writeString(of, infoChat + "\n", StandardOpenOption.APPEND);
        return true;
    }

    public SenderServiceImpl(String from, String to, String message, LocalDateTime messageSentTime) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.messageSentTime = messageSentTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public LocalDateTime getMessageSentTime() {
        return messageSentTime;
    }

    public void setMessageSentTime(LocalDateTime messageSentTime) {
        this.messageSentTime = messageSentTime;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("'Time:' HH:mm 'Date: 'yyyy-MM-dd");
        String formatted = messageSentTime.format(dateTimeFormatter);
        return " " + "Your_acc='" + from + '\'' + ",User_acc='" + to + '\'' + ", messageSent='" + message + '\'' + ", messageTime= " + formatted + '\'' + '}';
    }
}
