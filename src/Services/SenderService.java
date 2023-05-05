package Services;

import java.io.IOException;
import java.time.LocalDateTime;

public interface SenderService {
    boolean sendMessage(String fromEmail, String toEmail, String message, LocalDateTime localDateTime) throws IOException;
}
