package me.beardedorc.orcitems;

import org.bukkit.plugin.java.JavaPlugin;

public final class OrcItems extends JavaPlugin {

    private static OrcItems instance;

    public static OrcItems getInstance() {
        return instance;
    }
    private  static void setInstance(OrcItems instance) {
        OrcItems.instance = instance;
    }

    @Override
    public void onEnable() {
        setInstance(this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
