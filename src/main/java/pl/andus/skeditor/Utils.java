package pl.andus.skeditor;

import java.io.*;

//import static pl.andus.skeditor.Themes.*;

public class Utils {
    static String dirString = System.getProperty("user.home") + File.separator + "SkEditor";
    static File skDir = new File(dirString);
    static String settingsPath = dirString + File.separator + "Settings.skedit";
    static File settingsFile = new File(settingsPath);

    public static void OnStart() throws IOException {
        CheckDir();
        CheckSettings();
    }

    public static void CheckDir() {
        if (skDir.exists()) {
            System.out.println(skDir + " already exists");
        } else if (skDir.mkdirs()) {
            System.out.println(skDir + " was created");
        } else {
            System.out.println(skDir + " was not created");
        }
    }

    public static void CheckSettings() throws IOException {
        if (settingsFile.createNewFile()) {
            System.out.println("Settings File created!");
            WriteSettings();
        } else {
            System.out.println("Settings File already exists.");
            ReadSettings();
        }
    }

    public static void WriteSettings() throws IOException {
        String str = "Theme=light";
        FileOutputStream outputStream = new FileOutputStream(settingsFile);
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);

        outputStream.close();
    }

    public static void ReadSettings() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(settingsFile));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String themeline = sb.toString();
            //Theme(themeline);
        } finally {
            br.close();
        }
    }

    //Themes work

    /*public static void WriteTheme(String theme) throws IOException {
        String str = "Theme=" + theme;
        FileOutputStream outputStream = new FileOutputStream(settingsFile);
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);

        outputStream.close();
    }

    public static void Theme(String theme) {
        if (theme.equalsIgnoreCase("light")) {
            Light();
            System.out.println("[Theme] Light");
        } else if (theme.equalsIgnoreCase("dark")) {
            Dark();
            System.out.println("[Theme] Dark");
        } else if (theme.equalsIgnoreCase("nsky-light")) {
            NightSky(true);
            System.out.println("[Theme] Night Sky (Light)");
        } else if (theme.equalsIgnoreCase("nsky-dark")) {
            NightSky(false);
            System.out.println("[Theme] Night Sky (Dark)");
        } else {
            Light();
            System.out.println("[Theme] Unknown Theme...");
            System.out.println("[Theme] Setting theme to default.");
        }
        System.out.println(theme);
    }*/
}
