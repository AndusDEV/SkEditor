package pl.andus.skeditor;

import java.io.*;

import static pl.andus.skeditor.Themes.*;

public class Utils {
    static String dirString = System.getProperty("user.home") + File.separator + "SkEditor";
    static File skDir = new File(dirString);
    static String settingsPath = dirString + File.separator + "settings.txt";
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
        String str = "Theme: light";
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
    public static void WriteTheme(String theme) throws IOException {
        FileWriter fw = new FileWriter(settingsFile);

        for (int i = 0; i < 10; i++) {
            fw.write("Theme: " + theme);
        }

        fw.close();
    }

    public static void Theme(String theme) throws IOException {
        if (theme.equalsIgnoreCase("light")) {
            Light();
            WriteTheme("light");
            System.out.println("[Theme] Light");
        } else if (theme.equalsIgnoreCase("dark")) {
            Dark();
            WriteTheme("dark");
            System.out.println("[Theme] Dark");
        } else if (theme.equalsIgnoreCase("night-sky")) {
            NightSky();
            WriteTheme("night-sky");
            System.out.println("[Theme] Night Sky");
        } else if (theme.equalsIgnoreCase("metal")) {
            Metalic();
            WriteTheme("metal");
            System.out.println("[Theme] Metalic");
        } else {
            Light();
            System.out.println("[Theme] Unknown Theme...");
            System.out.println("[Theme] Setting theme to default.");
        }
        System.out.println(theme);
    }
}
