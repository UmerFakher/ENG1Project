package com.dragonboatrace;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

import de.tomgrill.gdxtesting.GdxTestRunner;

/* Check all assets exist */

@RunWith(GdxTestRunner.class)
public class AssetTest {

    @Test
    public void testTextureExists() {
        assertTrue(Gdx.files.internal("../tests/res/test_texture.png").exists());
    }

    @Test
    public void agileButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/agile_button_active.png").exists());
    }

    @Test
    public void agileButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/agile_button_inactive.png").exists());
    }

    @Test
    public void agileExists() {
        assertTrue(Gdx.files.internal("../core/assets/agile.png").exists());
    }

    @Test
    public void agilityPowerUpExists() {
        assertTrue(Gdx.files.internal("../core/assets/agility_power_up.png").exists());
    }

    @Test
    public void allPowerUpExists() {
        assertTrue(Gdx.files.internal("../core/assets/all_power_up.png").exists());
    }

    @Test
    public void backButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/back_button_active.png").exists());
    }

    @Test
    public void backButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/back_button_inactive.png").exists());
    }

    @Test
    public void backgroundExists() {
        assertTrue(Gdx.files.internal("../core/assets/background.png").exists());
    }

    @Test
    public void branchExists() {
        assertTrue(Gdx.files.internal("../core/assets/branch.png").exists());
    }

    @Test
    public void downArrowExists() {
        assertTrue(Gdx.files.internal("../core/assets/down_arrow.png").exists());
    }

    @Test
    public void dragonExists() {
        assertTrue(Gdx.files.internal("../core/assets/dragon.png").exists());
    }

    @Test
    public void easyButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/easy_button_active.png").exists());
    }

    @Test
    public void easyButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/easy_button_inactive.png").exists());
    }

    @Test
    public void easyExists() {
        assertTrue(Gdx.files.internal("../core/assets/easy.png").exists());
    }

    @Test
    public void enduranceButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/endurance_button_active.png").exists());
    }

    @Test
    public void enduranceButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/endurance_button_inactive.png").exists());
    }

    @Test
    public void enduranceExists() {
        assertTrue(Gdx.files.internal("../core/assets/endurance.png").exists());
    }

    @Test
    public void exitButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/exit_button_active.png").exists());
    }

    @Test
    public void exitButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/exit_button_inactive.png").exists());
    }

    @Test
    public void fastButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/fast_button_active.png").exists());
    }

    @Test
    public void fastButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/fast_button_inactive.png").exists());
    }

    @Test
    public void fastExists() {
        assertTrue(Gdx.files.internal("../core/assets/fast.png").exists());
    }

    @Test
    public void finishExists() {
        assertTrue(Gdx.files.internal("../core/assets/finish.png").exists());
    }

    @Test
    public void hardButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/hard_button_active.png").exists());
    }

    @Test
    public void hardButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/hard_button_inactive.png").exists());
    }

    @Test
    public void hardExists() {
        assertTrue(Gdx.files.internal("../core/assets/hard.png").exists());
    }

    @Test
    public void healthPowerUpExists() {
        assertTrue(Gdx.files.internal("../core/assets/health_power_up.png").exists());
    }

    @Test
    public void helpButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/help_button_active.png").exists());
    }

    @Test
    public void helpButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/help_button_inactive.png").exists());
    }

    @Test
    public void helpScreenInfoExists() {
        assertTrue(Gdx.files.internal("../core/assets/help_screen_info.png").exists());
    }

    @Test
    public void leafExists() {
        assertTrue(Gdx.files.internal("../core/assets/leaf.png").exists());
    }

    @Test
    public void lineExists() {
        assertTrue(Gdx.files.internal("../core/assets/line.png").exists());
    }

    @Test
    public void loadButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/load_button_active.png").exists());
    }

    @Test
    public void loadButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/load_button_inactive.png").exists());
    }

    @Test
    public void nightmareButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/nightmare_button_active.png").exists());
    }

    @Test
    public void nightmareButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/nightmare_button_inactive.png").exists());
    }

    @Test
    public void nightmareExists() {
        assertTrue(Gdx.files.internal("../core/assets/nightmare.png").exists());
    }

    @Test
    public void normalButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/normal_button_active.png").exists());
    }

    @Test
    public void normalButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/normal_button_inactive.png").exists());
    }

    @Test
    public void normalExists() {
        assertTrue(Gdx.files.internal("../core/assets/normal.png").exists());
    }

    @Test
    public void playButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/play_button_active.png").exists());
    }

    @Test
    public void playButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/play_button_inactive.png").exists());
    }

    @Test
    public void rockExists() {
        assertTrue(Gdx.files.internal("../core/assets/rock.png").exists());
    }

    @Test
    public void settingsButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/settings_button_active.png").exists());
    }

    @Test
    public void settingsButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/settings_button_inactive.png").exists());
    }

    @Test
    public void speedPowerUpExists() {
        assertTrue(Gdx.files.internal("../core/assets/speed_power_up.png").exists());
    }

    @Test
    public void staminaPowerUpExists() {
        assertTrue(Gdx.files.internal("../core/assets/stamina_power_up.png").exists());
    }

    @Test
    public void strongButtonActiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/strong_button_active.png").exists());
    }

    @Test
    public void strongButtonInactiveExists() {
        assertTrue(Gdx.files.internal("../core/assets/strong_button_inactive.png").exists());
    }

    @Test
    public void strongExists() {
        assertTrue(Gdx.files.internal("../core/assets/strong.png").exists());
    }

    @Test
    public void upArrowExists() {
        assertTrue(Gdx.files.internal("../core/assets/up_arrow.png").exists());
    }
}