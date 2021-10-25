package pl.andus.skeditor;

import org.fife.ui.rsyntaxtextarea.Token;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static pl.andus.skeditor.Main.*;

public class Themes {
    static BufferedImage nightSkyImg = null;
    static BufferedImage metalImg = null;
    static BufferedImage laserLemonImg = null;

    public static void Light() {
        // menu
        menuBar.setBackground(Color.white);
        // textarea
        textArea.setCurrentLineHighlightColor(new Color(255, 236, 121));
        textArea.setBackground(Color.white);
        textArea.setForeground(Color.black);
        // text
        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.blue;
        scheme.getStyle(Token.RESERVED_WORD_2).foreground = Color.orange;
        scheme.getStyle(Token.COMMENT_KEYWORD).foreground = Color.magenta;
        scheme.getStyle(Token.DATA_TYPE).foreground = new Color(53, 154, 255);
        scheme.getStyle(Token.OPERATOR).foreground = new Color(178, 51, 197);
        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.lightGray;
        scheme.getStyle(Token.WHITESPACE).background = Color.lightGray;
    }

    public static void Dark() {
        // menu
        menuBar.setBackground(Color.darkGray);
        menuBar.setForeground(Color.white);
        // textarea
        textArea.setCurrentLineHighlightColor(Color.gray);
        textArea.setBackground(Color.darkGray);
        textArea.setForeground(Color.white);
        //text
        scheme.getStyle(Token.RESERVED_WORD).foreground = new Color(53, 154, 255);
        scheme.getStyle(Token.RESERVED_WORD_2).foreground = Color.orange;
        scheme.getStyle(Token.COMMENT_KEYWORD).foreground = Color.magenta;
        scheme.getStyle(Token.DATA_TYPE).foreground = new Color(53, 154, 255);
        scheme.getStyle(Token.OPERATOR).foreground = new Color(178, 51, 197);
        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.lightGray;
    }

    public static void NightSky() {
        try {
            nightSkyImg = ImageIO.read(Themes.class.getClassLoader().getResourceAsStream("themes/night.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // menu
        menuBar.setBackground(Color.white);
        // textarea
        textArea.setBackgroundImage(nightSkyImg);
        textArea.setCurrentLineHighlightColor(new Color(0, 118, 255, 161));
        textArea.setForeground(Color.white);
        // text
        scheme.getStyle(Token.RESERVED_WORD).foreground = new Color(210, 85, 18);
        scheme.getStyle(Token.RESERVED_WORD_2).foreground = Color.orange;
        scheme.getStyle(Token.COMMENT_KEYWORD).foreground = Color.magenta;
        scheme.getStyle(Token.DATA_TYPE).foreground = new Color(110, 182, 248);
        scheme.getStyle(Token.OPERATOR).foreground = new Color(178, 51, 197);
        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.lightGray;
    }

    public static void Metalic() {
        try {
            metalImg = ImageIO.read(Themes.class.getClassLoader().getResourceAsStream("themes/metal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // menu
        menuBar.setBackground(new Color(201, 201, 201));
        // textarea
        textArea.setBackgroundImage(metalImg);
        textArea.setCurrentLineHighlightColor(new Color(201, 201, 201));
        textArea.setForeground(Color.black);
        // text
        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.blue;
        scheme.getStyle(Token.RESERVED_WORD_2).foreground = new Color(104, 22, 192);
        scheme.getStyle(Token.COMMENT_KEYWORD).foreground = Color.magenta;
        scheme.getStyle(Token.DATA_TYPE).foreground = new Color(53, 154, 255);
        scheme.getStyle(Token.OPERATOR).foreground = new Color(178, 51, 197);
        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.lightGray;
    }

    public static void LimeToYellow() {
        try {
            laserLemonImg = ImageIO.read(Themes.class.getClassLoader().getResourceAsStream("themes/lime-yellow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // menu
        menuBar.setBackground(new Color(136, 245, 119));
        // textarea
        textArea.setBackgroundImage(laserLemonImg);
        textArea.setCurrentLineHighlightColor(new Color(136, 245, 119, 142));
        textArea.setForeground(Color.black);
        // text
        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.blue;
        scheme.getStyle(Token.RESERVED_WORD_2).foreground = new Color(255, 0, 0);
        scheme.getStyle(Token.COMMENT_KEYWORD).foreground = Color.magenta;
        scheme.getStyle(Token.DATA_TYPE).foreground = new Color(31, 140, 255);
        scheme.getStyle(Token.OPERATOR).foreground = new Color(231, 80, 255);
        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.lightGray;
    }
}
