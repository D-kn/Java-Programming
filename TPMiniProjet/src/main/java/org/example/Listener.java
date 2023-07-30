package org.example;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Listener implements INotificationListener {
    private static int numModifications = 0;
    private String logFilePath;

    public Listener(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public void receivedNotification(Notifications notification) {
        numModifications++; // normaly should change at every modification
        logNotification(notification);
    }

    private void logNotification(Notifications notification) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write("[ADD] Movie Added: " + notification.getTitle() + ", Total Modifications: " + numModifications);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
