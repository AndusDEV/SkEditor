package pl.andus.skeditor;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    public About() {
        setTitle("About");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(350, 185));

        JEditorPane ep = new JEditorPane();
        ep.setEditable(false);
        ep.setContentType("text/html");
        ep.setText("<html>" +
                "<body>" +
                "<h1 align=\"center\">About</h1>" +
                "<h2 align=\"center\">SkriptEditor is an Mini IDE for SkriptLang<br> Made by: Andus</h2>" +
                "<a href=\"https://github.com/AndusDEV/SkEditor/\">Github Repo</a><br><a href=\"https://github.com/AndusDEV\">Andus's Github</a><br>" +
                "<a href=\"https://github.com/bobbylight/RSyntaxTextArea\">RSyntaxTextArea Github Repo</a>" +
                "</body>" +
                "</html>");

        panel.add(ep, BorderLayout.CENTER);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
