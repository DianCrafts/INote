package com.company;

import java.io.*;

public class FileUtils implements Serializable {
    private static String data = "";
    private static final String NOTES_PATH = "./notes/";


    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }


    public static String fileReader(File file) {
        String line = null;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                data = data + line + "\n";
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void fileWriter(String content) {
        //TODO: write content on file
        String fileName = getProperFileName(content);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(NOTES_PATH + "hello.txt"));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void output(String content) {
        FileOutputStream fop = null;
        try {
            File file = new File(NOTES_PATH);
            fop = new FileOutputStream(file, true);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] nm = content.getBytes();
            fop.write(nm);
            fop.flush();
            fop.close();
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String inputStream(File file) {
        String b = "";
        try (FileInputStream fis = new FileInputStream(file)) {

            System.out.println("Total file size to read (in bytes) : " + fis.available());

            int content;

            while ((content = fis.read()) != -1) {
                // convert to char and display it
                char a = (char) content;
                b = String.valueOf(a) + b;
            }
            System.out.println(b);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
    //TODO: Phase1: define method here for reading file with InputStream
    //TODO: Phase1: define method here for writing file with OutputStream

    //TODO: Phase2: proper methods for handling serialization

    private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
        }
        return System.currentTimeMillis() + "_new file.txt";
    }
}

