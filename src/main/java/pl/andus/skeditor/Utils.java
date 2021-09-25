package pl.andus.skeditor;

import java.io.*;

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
            String everything = sb.toString();
        } finally {
            br.close();
        }
    }
}
