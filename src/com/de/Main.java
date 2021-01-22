package com.de;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        char [] barAnimation = {'\\', '|', '/', '-'};
        StringBuilder sb = new StringBuilder("----------");
        String fileName = "avgfile.txt";
        //String editedFile = "avgfile.txt";
        Path path = Paths.get(fileName);

        long numberOfLines = 0;
        try {
            numberOfLines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("file number of lines: " + numberOfLines);

        //editFile(fileName, editedFile);

        progressBarReadFile(fileName, numberOfLines, sb, barAnimation);

        //progressBarTest(sb, barAnimation);

    }

    private static void progressBarTest(StringBuilder sb, char[] barAnimation) {
        for (int i = 0; i <= 100; i++){
            if (i > 99) {
                System.out.print("\r[" + sb.toString() + "] " + i + "% done.");
            } else {
                System.out.print("\r[" + ((i + 1) % 10 == 0 ? sb.replace(i / 10, i / 10 + 1, "#") : sb.toString()) + "] " + i + "% " + barAnimation[i % 4] +
                        " processing...");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void progressBarReadFile(String fileName, long numberOfLines, StringBuilder sb, char[] barAnimation) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            long lineCounter = 1;
            while ((line = br.readLine()) != null){
                int percent = (int) ((lineCounter * 100) / numberOfLines);

                if (percent > 99) {
                    System.out.print("\r" + "\"" + line + "\"" + " line number: " + lineCounter + " [##########] " + percent + "% done.");
                } else {
                    System.out.print("\r" + "\"" + line + "\"" + " line number: " + lineCounter + " [" + ((percent + 1) % 10 == 0 ? sb.replace(percent / 10, percent / 10 + 1, "#") : sb.toString()) + "] " + percent + "% " + barAnimation[percent % 4] +
                            " processing...");
                }
                lineCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void editFile(String fileName, String editedFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(editedFile));
            String currentLine;
            long counter = 1;
            //String lineToRemove = "type here the line to remove from the text";

            while((currentLine = reader.readLine()) != null && counter < 25000000) {
                // trim newline when comparing with lineToRemove
                //String trimmedLine = currentLine.trim();
                //if(trimmedLine.equals(lineToRemove)){ continue; }
                writer.write(currentLine + System.getProperty("line.separator"));
                counter++;
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
