package pl.andus.skeditor;

import org.fife.ui.rsyntaxtextarea.Token;

import java.awt.*;

import static pl.andus.skeditor.Main.*;

public class Themes {
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
    }

    public static void Dark() {
        // menu
        menuBar.setBackground(Color.darkGray);
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

    public static void NightSky(boolean dark) {
        if(dark) {
            // menu
            menuBar.setBackground(Color.darkGray);
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
        } else {
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
        }
    }
}
