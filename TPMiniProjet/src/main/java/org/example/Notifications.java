package org.example;

import java.util.EventObject;

public class Notifications extends EventObject {
    private String title;

    public Notifications(String title, Object source) {
        super(source);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
