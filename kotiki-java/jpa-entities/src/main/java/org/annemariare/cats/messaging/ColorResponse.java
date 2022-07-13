package org.annemariare.cats.messaging;

import org.annemariare.cats.enums.Color;

import java.io.Serializable;

public class ColorResponse implements Serializable {
    private Color color;
    private String username;

    public ColorResponse(Color color, String username) {
        this.color = color;
        this.username = username;
    }

    public ColorResponse() {
    }

    public Color getColor() {
        return color;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "ColorResponse{" +
                "color=" + color +
                ", username='" + username + '\'' +
                '}';
    }
}
