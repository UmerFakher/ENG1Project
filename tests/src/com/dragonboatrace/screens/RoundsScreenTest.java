package com.dragonboatrace.screens;

import com.dragonboatrace.entities.boats.BoatType;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class RoundsScreenTest {

    @Test
    public void saveToFile() {
        BoatType b = BoatType.FAST;
        RoundsScreen.saveToFile("testfile.txt", b, 4.3f, 1,0, "__testing_boat__");
        File file = new File("testfile.txt");
        assertTrue(file.exists());

        List<String> saveData = new ArrayList<>();
        List<String> expectedData = new ArrayList<>();

        expectedData.add("0");
        expectedData.add("4.3");
        expectedData.add("1");
        expectedData.add("0");

        try {
            File myObj = new File("testfile.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                saveData.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assertEquals(expectedData, saveData);

        b = BoatType.AGILE;
        RoundsScreen.saveToFile("testfile.txt", b, 6.2f, 3, 1, "__testing_boat__");
        file = new File("testfile.txt");
        assertTrue(file.exists());

        saveData = new ArrayList<>();
        expectedData = new ArrayList<>();

        expectedData.add("1");
        expectedData.add("6.2");
        expectedData.add("3");
        expectedData.add("1");

        try {
            File myObj = new File("testfile.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                saveData.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assertEquals(expectedData, saveData);

        file.delete();
    }
}