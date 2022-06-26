package io.github.axst.utils;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LinkOpener {
    public static void openLink(String url) {
        Desktop desktop = Desktop.getDesktop();
        if (desktop != null) {
            try {
                desktop.browse(new URL(url).toURI());
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
