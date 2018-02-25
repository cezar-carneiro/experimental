package com.oproom.model;

/**
 * Created by Cezar Carneiro on 24/1/2018.
 */

public class Clipboard {

    private String text;

    public Clipboard() {
    }

    public Clipboard(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
