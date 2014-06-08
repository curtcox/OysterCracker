package com.mycompany.myapp.screens;

import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.mycompany.myapp.Registry;
import com.mycompany.myapp.log.LogWriter;

/**
 *
 * @author Curt
 */
final class IssueReporter {

    static void sendEmail() {
        String[] recipients = new String[]{"curtcox@gmail.com"};
        String subject = "Oyster Cracker Issue Report";
        getDisplay().sendMessage(recipients, subject, createMessage());
    }
    
    static Display getDisplay() {
        return Registry.get(Display.class);
    }

    static Message createMessage() {
        return new Message(createContent());
    }

    private static String createContent() {
        StringBuilder out = new StringBuilder();
        out.append(HomeScreen.VERSION + "/r/n");
        out.append(getLogWriter().dump());
        return out.toString();
    }

    private static LogWriter getLogWriter() {
        return LogWriter.of();
    }

}