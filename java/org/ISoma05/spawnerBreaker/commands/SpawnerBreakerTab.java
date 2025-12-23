package org.ISoma05.spawnerBreaker.commands;

import org.bukkit.command.TabCompleter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnerBreakerTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (!(sender instanceof Player player)) return completions;

        if (args.length == 1) {
            if (player.hasPermission("Spawner.Breaker.help"))
                completions.add("help");
            if (player.hasPermission("Spawner.Breaker.reload"))
                completions.add("reload");
            if (player.hasPermission("Spawner.Breaker.setperm"))
                completions.add("setpermission");
            if (player.hasPermission("Spawner.Breaker.silktouch"))
                completions.add("silktouch");

            return completions;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("silktouch")) {
            if (player.hasPermission("Spawner.Breaker.silktouch")) {
                completions.add("enabled");
                completions.add("disabled");
            }
        }

        return completions;
    }
}
