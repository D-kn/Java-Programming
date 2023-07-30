package org.example;

public interface INotificationGenerator {
    void register(Listener listener);
    void unregister(Listener listener);
    void notifyListeners(String title);
}
