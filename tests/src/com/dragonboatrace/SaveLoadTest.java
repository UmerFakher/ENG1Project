package com.dragonboatrace;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.screens.MainGameScreen;
import com.dragonboatrace.screens.MainMenuScreen;
import com.dragonboatrace.screens.RoundsScreen;
import com.dragonboatrace.tools.Lane;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RunWith(GdxTestRunner.class)
public class SaveLoadTest {

    Lane l = new Lane(new Vector2(0, 0), 50, 0, 0);

    @Test
    public void saveLoadTest() {
        DragonBoatRace r1 = new DragonBoatRace();
        r1.setDifficulty(2);
        r1.setRound(2);
        r1.setScreen(new RoundsScreen(r1, new Boat(BoatType.FAST, l, ""),""));
        ((RoundsScreen)r1.getScreen()).saveToFile("testing_file.txt");

        DragonBoatRace r2 = new DragonBoatRace();
        r2.setScreen(new MainMenuScreen(r2));
        File file = new File("testing_file.txt");
        ((MainMenuScreen)r2.getScreen()).loadSavefile(file);

        Assert.assertEquals(r1.getDifficulty(), r2.getDifficulty());
        Assert.assertEquals(r1.getRound(), r2.getRound());
        Assert.assertEquals(BoatType.FAST, ((MainGameScreen)r2.getScreen()).getRace().getPlayer().getBoatType());

        file.delete();
    }

    @Test
    public void invalidFileTest(){

        File oldFile = new File("testing_file.txt");

        try {
            File newFile = new File("testing_file.txt");
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("testing_file.txt", false);
            myWriter.write("LAKIJFKLAJS:LFb;\nsjdf;slajkdf\\n\noihfn;\no\noif;oid\n\nnoidhf;oisjd;fl");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        DragonBoatRace r2 = new DragonBoatRace();
        r2.setScreen(new MainMenuScreen(r2));
        File file = new File("testing_file.txt");
        ((MainMenuScreen)r2.getScreen()).loadSavefile(file);

        Assert.assertTrue(r2.getScreen() instanceof MainMenuScreen);

        try {
            FileWriter myWriter = new FileWriter("testing_file.txt", false);
            myWriter.write("6\ngs\n7\n99\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Assert.assertTrue(r2.getScreen() instanceof MainMenuScreen);

        file.delete();
    }
}
