package com.dragonboatrace;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

import de.tomgrill.gdxtesting.GdxTestRunner;

/**
 * TUA_ASSETS - Check all assets exist and are in the correct place.
 *
 * All required assets should be in the assets directory and named according ot the design.
 */
@RunWith(GdxTestRunner.class)
public class AssetTest {

    @Test
    public void textureExistsTest() {
        assertTrue(Gdx.files.internal("../tests/res/test_texture.png").exists());
    }

    @Test
    public void agileButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/agile_button_active.png").exists());
    }

    @Test
    public void agileButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/agile_button_inactive.png").exists());
    }

    @Test
    public void agileExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/agile.png").exists());
    }

    @Test
    public void agilityPowerUpExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/agility_power_up.png").exists());
    }

    @Test
    public void allPowerUpExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/all_power_up.png").exists());
    }

    @Test
    public void backButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/back_button_active.png").exists());
    }

    @Test
    public void backButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/back_button_inactive.png").exists());
    }

    @Test
    public void backgroundExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/background.png").exists());
    }

    @Test
    public void branchExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/branch.png").exists());
    }

    @Test
    public void downArrowExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/down_arrow.png").exists());
    }

    @Test
    public void dragonExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/dragon.png").exists());
    }

    @Test
    public void easyButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/easy_button_active.png").exists());
    }

    @Test
    public void easyButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/easy_button_inactive.png").exists());
    }

    @Test
    public void easyExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/easy.png").exists());
    }

    @Test
    public void enduranceButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/endurance_button_active.png").exists());
    }

    @Test
    public void enduranceButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/endurance_button_inactive.png").exists());
    }

    @Test
    public void enduranceExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/endurance.png").exists());
    }

    @Test
    public void exitButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/exit_button_active.png").exists());
    }

    @Test
    public void exitButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/exit_button_inactive.png").exists());
    }

    @Test
    public void fastButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/fast_button_active.png").exists());
    }

    @Test
    public void fastButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/fast_button_inactive.png").exists());
    }

    @Test
    public void fastExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/fast.png").exists());
    }

    @Test
    public void finishExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/finish.png").exists());
    }

    @Test
    public void hardButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/hard_button_active.png").exists());
    }

    @Test
    public void hardButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/hard_button_inactive.png").exists());
    }

    @Test
    public void hardExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/hard.png").exists());
    }

    @Test
    public void healthPowerUpExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/health_power_up.png").exists());
    }

    @Test
    public void helpButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/help_button_active.png").exists());
    }

    @Test
    public void helpButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/help_button_inactive.png").exists());
    }

    @Test
    public void helpScreenInfoExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/help_screen_info.png").exists());
    }

    @Test
    public void leafExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/leaf.png").exists());
    }

    @Test
    public void lineExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/line.png").exists());
    }

    @Test
    public void loadButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/load_button_active.png").exists());
    }

    @Test
    public void loadButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/load_button_inactive.png").exists());
    }

    @Test
    public void nightmareButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/ultra_button_active.png").exists());
    }

    @Test
    public void nightmareButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/ultra_button_inactive.png").exists());
    }

    @Test
    public void nightmareExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/ultra.png").exists());
    }

    @Test
    public void normalButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/normal_button_active.png").exists());
    }

    @Test
    public void normalButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/normal_button_inactive.png").exists());
    }

    @Test
    public void normalExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/normal.png").exists());
    }

    @Test
    public void playButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/play_button_active.png").exists());
    }

    @Test
    public void playButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/play_button_inactive.png").exists());
    }

    @Test
    public void rockExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/rock.png").exists());
    }

    @Test
    public void settingsButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/settings_button_active.png").exists());
    }

    @Test
    public void settingsButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/settings_button_inactive.png").exists());
    }

    @Test
    public void speedPowerUpExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/speed_power_up.png").exists());
    }

    @Test
    public void staminaPowerUpExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/stamina_power_up.png").exists());
    }

    @Test
    public void strongButtonActiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/strong_button_active.png").exists());
    }

    @Test
    public void strongButtonInactiveExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/strong_button_inactive.png").exists());
    }

    @Test
    public void strongExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/strong.png").exists());
    }

    @Test
    public void upArrowExistsTest() {
        assertTrue(Gdx.files.internal("../core/assets/up_arrow.png").exists());
    }
}