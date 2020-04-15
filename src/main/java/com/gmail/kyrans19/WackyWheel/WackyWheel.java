package com.gmail.kyrans19.WackyWheel;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Location;

/**
 * Main plugin class
 */
public final class WackyWheel extends JavaPlugin {
    WackyWheelCommandExecutor executor;
    Location wheelLocation;
    String version = "1.2-SNAPSHOT";

    public String getVersion() {
        return version;
    }

    @Override
    public void onEnable() {
        /**
         * Method executed on plugin load
         */
        executor = new WackyWheelCommandExecutor(this);
        try {
            wheelLocation = WackyWheelReadWrite.readHomeFromJson();
        } catch (Exception e) {
            getLogger().warning("Wacky Wheel location not set, please set with /ww setWheelLocation");
        }

        this.getCommand("setWheelLocation").setExecutor(executor);
        this.getCommand("version").setExecutor(executor);
        getLogger().info("Wacky Wheel version " + this.getVersion() +  " has started!");
    }

    @Override
    public void onDisable() {
        /**
         * Method executed on plugin unload
         */
    }
}