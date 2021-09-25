package pl.andus.skeditor;

import org.fife.ui.autocomplete.*;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

import static pl.andus.skeditor.Themes.*;
import static pl.andus.skeditor.Utils.*;

public class Main extends JFrame {

    static RSyntaxTextArea textArea = new RSyntaxTextArea(45, 150);
    static JMenuBar menuBar = new JMenuBar();
    static SyntaxScheme scheme = textArea.getSyntaxScheme();
    static RTextScrollPane sp = new RTextScrollPane(textArea);
    static JPanel cp = new JPanel(new BorderLayout());

    public Main() throws IOException {
        OnStart();

        // Menu
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
        JMenuItem variablesTemp = new JMenuItem("Variables");
        JMenu onActTemp = new JMenu("On Action");

        JMenuItem onDeathTemp = new JMenuItem("On death");
        JMenuItem onJoinTemp = new JMenuItem("On join");
        JMenuItem onFJoinTemp = new JMenuItem("On first join");
        JMenuItem onLeaveTemp = new JMenuItem("On leave");
        onActTemp.add(onDeathTemp);
        onActTemp.add(onJoinTemp);
        onActTemp.add(onFJoinTemp);
        onActTemp.add(onLeaveTemp);

        templateMenu.add(cmdTemp);
        templateMenu.add(variablesTemp);
        templateMenu.add(onActTemp);

        JMenuItem lightTheme = new JMenuItem("Light");
        JMenuItem darkTheme = new JMenuItem("Dark");
        JMenu nSkyTheme = new JMenu("Night Sky");
        JMenuItem nSkyLight = new JMenuItem("Light");
        JMenuItem nSkyDark = new JMenuItem("Dark");

        nSkyTheme.add(nSkyLight);
        nSkyTheme.add(nSkyDark);

        themesMenu.add(lightTheme);
        themesMenu.add(darkTheme);
        themesMenu.add(nSkyTheme);

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

        textArea.setSyntaxEditingStyle("text/sk");
        textArea.setCodeFoldingEnabled(true);
        textArea.setHyperlinksEnabled(true);
        cp.add(sp);

        menuBar.setBackground(Color.white);
        textArea.setCurrentLineHighlightColor(new Color(255, 236, 121));
        textArea.setBackground(Color.white);
        textArea.setForeground(Color.black);
        CompletionProvider provider = createCompletionProvider();
        AutoCompletion ac = new AutoCompletion(provider);
        ac.install(textArea);
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
                File fi = new File(j.getSelectedFile().getAbsolutePath() + ".sk");

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
                "   trigger:\n" +
                "       # what command should do\n" +
                "       # e.g. 'send \"hello\" to player'. It will send message in Quote to player that sent the command.\n", textArea.getCaretPosition()));

        variablesTemp.addActionListener(e -> {
            textArea.setCaretPosition(0);
            textArea.insert("variables:\n" +
                    "   {gvar} = 0 # Global Variable. You can change it's name in '{ }'. You can change value of it after '='\n" +
                    "   {_lvar} = 0 # Local Variable. You can change it's name in '{_ }'. You can change value of it after '='\n", textArea.getCaretPosition());
        });

        onDeathTemp.addActionListener(e -> textArea.insert("\n\non death of player:\n" +
                "   if attacker is a player: # check if attacker is a player\n" +
                "       broadcast \"&cPlayer &4&l%victim% &chas been killed by &a&l%attacker%\" # send message what player killed\n" +
                "   else:\n" +
                "       broadcast \"&cPlayer &4&l%victim% &cdied.\"\n", textArea.getCaretPosition()));

        onJoinTemp.addActionListener(e -> textArea.insert("\n\non join:\n" +
                "   broadcast \"&9Player &a&l%player% &9joined on the server\"\n", textArea.getCaretPosition()));

        onFJoinTemp.addActionListener(e -> textArea.insert("\n\non first join:\n" +
                "   broadcast \"&9Player &a&l&o%player% &9joined on server for the first time\"\n", textArea.getCaretPosition()));

        onLeaveTemp.addActionListener(e -> textArea.insert("\n\non leave:\n" +
                "   broadcast \"&9Player &c&l%player% &9left the server\"\n", textArea.getCaretPosition()));

        lightTheme.addActionListener(e -> {
            Light();
            //WriteTheme("light");
        });

        darkTheme.addActionListener(e -> {
            Dark();
            //WriteTheme("dark");
        });

        nSkyLight.addActionListener(e -> {
            NightSky(false);
            //WriteTheme("nsky-light");
        });
        nSkyDark.addActionListener(e -> {
            NightSky(true);
            //WriteTheme("nsky-dark");
        });

        about.addActionListener(e -> new About());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                String laf = UIManager.getSystemLookAndFeelClassName();
                UIManager.setLookAndFeel(laf);
            } catch (Exception ignored) {}
            try {
                new Main().setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private CompletionProvider createCompletionProvider() {
        DefaultCompletionProvider provider = new DefaultCompletionProvider();

        provider.addCompletion(new ShorthandCompletion(provider, "cmd", "command:", "Command"));
        provider.addCompletion(new BasicCompletion(provider, "if"));
        provider.addCompletion(new BasicCompletion(provider, "else"));
        provider.addCompletion(new BasicCompletion(provider, "trigger"));
        provider.addCompletion(new BasicCompletion(provider, "permission"));
        provider.addCompletion(new BasicCompletion(provider, "permission message"));
        provider.addCompletion(new BasicCompletion(provider, "variables"));

        return provider;
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