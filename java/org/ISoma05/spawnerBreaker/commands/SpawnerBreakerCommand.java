package org.ISoma05.spawnerBreaker.commands;

import org.ISoma05.spawnerBreaker.SpawnerBreaker;
import org.ISoma05.spawnerBreaker.data.SpawnerBreakerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnerBreakerCommand implements CommandExecutor {

    private final SpawnerBreaker plugin;
    private final SpawnerBreakerManager manager;

    public SpawnerBreakerCommand(SpawnerBreaker plugin, SpawnerBreakerManager manager) {
        this.plugin = plugin;
        this.manager = manager;
    }

    private String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(color(plugin.getConfig().getString("messages.help_header")));

        sender.sendMessage(color("&b/spawnerbreaker help &7- Show this help menu"));
        sender.sendMessage(color("&b/spawnerbreaker reload &7- Reload config.yml"));
        sender.sendMessage(color("&b/spawnerbreaker setpermission <node> &7- Set required permission"));
        sender.sendMessage(color("&b/spawnerbreaker silktouch <enabled|disabled> &7- Toggle Silk Touch requirement"));

        sender.sendMessage(color(plugin.getConfig().getString("messages.help_footer")));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "help":
                if (!sender.hasPermission("Spawner.Breaker.help")) {
                    sender.sendMessage("§cYou do not have permission.");
                    return true;
                }
                sendHelp(sender);
                return true;

            case "reload":
                if (!sender.hasPermission("Spawner.Breaker.reload")) {
                    sender.sendMessage("§cYou do not have permission.");
                    return true;
                }
                plugin.reloadConfig();
                sender.sendMessage(color(manager.prefix() + plugin.getConfig().getString("messages.reloaded")));
                return true;

            case "setpermission":
                if (!sender.hasPermission("Spawner.Breaker.setperm")) {
                    sender.sendMessage("§cYou do not have permission.");
                    return true;
                }

                if (args.length < 2) {
                    sender.sendMessage("§cUsage: /spawnerbreaker setpermission <node>");
                    return true;
                }

                manager.setRequiredPermission(args[1]);

                sender.sendMessage(color(manager.prefix() +
                        plugin.getConfig().getString("messages.setpermission_success")
                                .replace("%perm%", args[1])));
                return true;

            case "silktouch":
                if (!sender.hasPermission("Spawner.Breaker.silktouch")) {
                    sender.sendMessage("§cYou do not have permission.");
                    return true;
                }

                if (args.length < 2) {
                    sender.sendMessage("§cUsage: /spawnerbreaker silktouch <enabled|disabled>");
                    return true;
                }

                String state = args[1].toLowerCase();
                if (!state.equals("enabled") && !state.equals("disabled")) {
                    sender.sendMessage("§cState must be: enabled or disabled");
                    return true;
                }

                boolean enabled = state.equals("enabled");
                plugin.getConfig().set("silk_touch_required", enabled);
                plugin.saveConfig();

                sender.sendMessage(color(manager.prefix() +
                        plugin.getConfig().getString("messages.silktouch_updated")
                                .replace("%state%", state)));
                return true;
        }

        sendHelp(sender);
        return true;
    }
}
