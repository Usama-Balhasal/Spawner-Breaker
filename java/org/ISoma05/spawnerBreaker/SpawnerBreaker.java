package org.ISoma05.spawnerBreaker;

import org.ISoma05.spawnerBreaker.commands.SpawnerBreakerCommand;
import org.ISoma05.spawnerBreaker.commands.SpawnerBreakerTab;
import org.ISoma05.spawnerBreaker.listeners.SpawnerBreakerListener;
import org.ISoma05.spawnerBreaker.data.SpawnerBreakerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnerBreaker extends JavaPlugin {

    private static SpawnerBreaker instance;
    private SpawnerBreakerManager manager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        manager = new SpawnerBreakerManager(this);

        getServer().getPluginManager().registerEvents(new SpawnerBreakerListener(this), this);

        getCommand("spawnerbreaker").setExecutor(new SpawnerBreakerCommand(this, manager));

        getCommand("spawnerbreaker").setTabCompleter(new SpawnerBreakerTab());

        getLogger().info("SpawnerBreaker has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SpawnerBreaker has been disabled!");
    }

    public static SpawnerBreaker getInstance() {
        return instance;
    }
}
