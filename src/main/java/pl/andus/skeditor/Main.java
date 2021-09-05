package pl.andus.skeditor;

import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.*;

public class Main extends JFrame {

    static RSyntaxTextArea textArea = new RSyntaxTextArea(45, 150);

    public Main() {
        // Menu

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu templateMenu = new JMenu("Templates");
        JMenu themesMenu = new JMenu("Themes");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem newF = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");

        fileMenu.add(newF);
        fileMenu.add(open);
        fileMenu.add(save);

        JMenuItem selectAll = new JMenuItem("Select All");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");

        editMenu.add(selectAll);
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        JMenuItem cmdTemp = new JMenuItem("Command");

        templateMenu.add(cmdTemp);

        JMenuItem lightTheme = new JMenuItem("Light");
        JMenuItem darkTheme = new JMenuItem("Dark");

        themesMenu.add(lightTheme);
        themesMenu.add(darkTheme);

        JMenuItem about = new JMenuItem("About");

        helpMenu.add(about);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(templateMenu);
        menuBar.add(themesMenu);
        menuBar.add(helpMenu);

        // Text Area

        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
        atmf.putMapping("text/sk", "pl.andus.skeditor.sksyntax");

        JPanel cp = new JPanel(new BorderLayout());

        textArea.setSyntaxEditingStyle("text/sk");
        textArea.setCodeFoldingEnabled(true);
        textArea.setHyperlinksEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        cp.add(sp);

        SyntaxScheme scheme = textArea.getSyntaxScheme();
        menuBar.setBackground(Color.white);
        textArea.setBackground(Color.white);
        textArea.setForeground(Color.black);
        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.blue;
        scheme.getStyle(Token.RESERVED_WORD_2).foreground = Color.orange;
        scheme.getStyle(Token.COMMENT_KEYWORD).foreground = Color.magenta;
        scheme.getStyle(Token.DATA_TYPE).foreground = new Color(53, 154, 255);
        scheme.getStyle(Token.OPERATOR).foreground = new Color(178, 51, 197);
        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.lightGray;
        setContentPane(cp);
        setTitle("Skript Editor " + Constants.version);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        newF.addActionListener(e -> new ShowDialog());
        open.addActionListener(e -> {
            JFileChooser j = new JFileChooser("c:");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Skript Files (.sk)", "sk");
            j.setFileFilter(filter);

            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    String s1;
                    StringBuilder sl;

                    FileReader fr = new FileReader(fi);

                    BufferedReader br = new BufferedReader(fr);

                    sl = new StringBuilder(br.readLine());

                    while ((s1 = br.readLine()) != null) {
                        sl.append("\n").append(s1);
                    }

                    textArea.setText(sl.toString());
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(this, evt.getMessage());
                }
            }
        });
        save.addActionListener(e -> {
            JFileChooser j = new JFileChooser("c:");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Skript Files (.sk)", "sk");
            j.setFileFilter(filter);

            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    FileWriter wr = new FileWriter(fi, false);

                    BufferedWriter w = new BufferedWriter(wr);

                    w.write(textArea.getText());

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(this, evt.getMessage());
                }
            } else if (r == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(this, "the user cancelled the operation");
            }
        });

        selectAll.addActionListener(e -> textArea.selectAll());
        cut.addActionListener(e -> textArea.cut());
        copy.addActionListener(e -> textArea.copy());
        paste.addActionListener(e -> textArea.paste());

        cmdTemp.addActionListener(e -> textArea.insert("\n\ncommand /cmd:\n" +
                "   description: Description of what this command does\n" +
                "   usage: How to use the command, e.g. /cmd\n" +
                "   permission: your.permission\n" +
                "   permission message: &4You don't have permission to that command\n" +
                "   executable by: players/console/players and console\n" +
                "   aliases: /c, /command # list of all command aliases\n" +
                "   trigger:" +
                "       # what command should do" +
                "       # e.g. 'send \"hello\" to player'. It will send message in Quote to player that sent the command.\n", textArea.getCaretPosition()));

        lightTheme.addActionListener(e -> {
            // menu
            menuBar.setBackground(Color.white);
            // textarea
            textArea.setBackground(Color.white);
            textArea.setForeground(Color.black);
            // text
            scheme.getStyle(Token.RESERVED_WORD).foreground = Color.blue;
            scheme.getStyle(Token.RESERVED_WORD_2).foreground = Color.orange;
            scheme.getStyle(Token.COMMENT_KEYWORD).foreground = Color.magenta;
            scheme.getStyle(Token.DATA_TYPE).foreground = new Color(53, 154, 255);
            scheme.getStyle(Token.OPERATOR).foreground = new Color(178, 51, 197);
            scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.lightGray;
        });

        darkTheme.addActionListener(e -> {
            // menu
            menuBar.setBackground(Color.darkGray);
            // textarea
            textArea.setBackground(Color.darkGray);
            textArea.setForeground(Color.white);
            //text
            scheme.getStyle(Token.RESERVED_WORD).foreground = new Color(53, 154, 255);
            scheme.getStyle(Token.RESERVED_WORD_2).foreground = Color.orange;
            scheme.getStyle(Token.COMMENT_KEYWORD).foreground = Color.magenta;
            scheme.getStyle(Token.DATA_TYPE).foreground = new Color(53, 154, 255);
            scheme.getStyle(Token.OPERATOR).foreground = new Color(178, 51, 197);
            scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.lightGray;
        });

        about.addActionListener(e -> new About());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

}

class ShowDialog {
    public ShowDialog() {

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(new JFrame(), "Would You Like to Save your Previous Script First?", "Save File?",
                    dialogButton);

        if(dialogResult == JOptionPane.YES_OPTION) {
            JFileChooser j = new JFileChooser("c:");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Skript Files (.sk)", "sk");
            j.setFileFilter(filter);

            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    FileWriter wr = new FileWriter(fi, false);

                    BufferedWriter w = new BufferedWriter(wr);

                    w.write(Main.textArea.getText());

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(new JFrame(), evt.getMessage());
                }
            } else if (r == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(new JFrame(), "the user cancelled the operation");
            }
        }
    }
}