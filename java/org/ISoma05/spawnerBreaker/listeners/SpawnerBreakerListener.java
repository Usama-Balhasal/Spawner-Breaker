package org.ISoma05.spawnerBreaker.listeners;

import org.ISoma05.spawnerBreaker.SpawnerBreaker;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class SpawnerBreakerListener implements Listener {

    private final SpawnerBreaker plugin;

    public SpawnerBreakerListener(SpawnerBreaker plugin) {
        this.plugin = plugin;
    }

    private void dropXP(Block block) {
        block.getWorld().spawn(block.getLocation(), ExperienceOrb.class, orb -> orb.setExperience(15));
    }

    @EventHandler
    public void onSpawnerBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (block.getType() != Material.SPAWNER) return;

        String requiredPerm = plugin.getConfig().getString("required_break_permission");
        boolean silkRequired = plugin.getConfig().getBoolean("silk_touch_required");
        String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));

        ItemStack tool = player.getInventory().getItemInMainHand();
        boolean hasSilk = tool != null && tool.containsEnchantment(Enchantment.SILK_TOUCH);

        // 1. NO PERMISSION → normal break + XP, no drop
        if (!player.hasPermission(requiredPerm)) {
            player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfig().getString("no_permission_drop")));
            dropXP(block);
            return;
        }

        // 2. HAS PERMISSION but Silk Touch is required and NOT present → no drop
        if (silkRequired && !hasSilk) {
            player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfig().getString("messages.no_silk_touch")));
            dropXP(block);
            return;
        }

        // 3. PLAYER CAN RECEIVE SPAWNER → no XP drop
        event.setExpToDrop(0);

        BlockState state = block.getState();
        if (!(state instanceof CreatureSpawner cs)) return;

        ItemStack spawnerItem = new ItemStack(Material.SPAWNER);
        BlockStateMeta meta = (BlockStateMeta) spawnerItem.getItemMeta();

        CreatureSpawner newSpawnerState = (CreatureSpawner) meta.getBlockState();
        newSpawnerState.setSpawnedType(cs.getSpawnedType());
        meta.setBlockState(newSpawnerState);

        spawnerItem.setItemMeta(meta);

        block.getWorld().dropItemNaturally(block.getLocation(), spawnerItem);
    }
    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() != Material.SPAWNER) return;

        ItemStack item = event.getItemInHand();
        if (item == null || item.getType() != Material.SPAWNER) return;

        if (!(item.getItemMeta() instanceof BlockStateMeta meta)) return;
        if (!(meta.getBlockState() instanceof CreatureSpawner storedSpawner)) return;

        Block block = event.getBlockPlaced();
        BlockState state = block.getState();

        if (state instanceof CreatureSpawner placedSpawner) {
            placedSpawner.setSpawnedType(storedSpawner.getSpawnedType());
            placedSpawner.update(true, false);
        }
    }

}
