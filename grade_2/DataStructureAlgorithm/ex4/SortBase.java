package ex4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class SortBase {
    protected int n = 50000;
    protected int[] array;

    public SortBase(String fileName) {
        array = new int[n];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                array[i] = Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.out.println("Reader Error");
        }
    }

    public abstract void sort();

    public void output(String fileName) {
        try (var writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (var line : array)
                writer.println(line);
        } catch (IOException e) {
            System.out.println("Writer Error");
            return;
        }
    }
}