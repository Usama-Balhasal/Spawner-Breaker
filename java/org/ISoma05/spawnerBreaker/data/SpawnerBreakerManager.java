package org.ISoma05.spawnerBreaker.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.ISoma05.spawnerBreaker.SpawnerBreaker;

public class SpawnerBreakerManager {

    private final SpawnerBreaker plugin;

    public SpawnerBreakerManager(SpawnerBreaker plugin) {
        this.plugin = plugin;
    }

    public String getRequiredPermission() {
        return plugin.getConfig().getString("required_break_permission");
    }

    public void setRequiredPermission(String permission) {
        plugin.getConfig().set("required_break_permission", permission);
        plugin.saveConfig();
    }

    public String prefix() {
        return plugin.getConfig().getString("prefix");
    }
}
