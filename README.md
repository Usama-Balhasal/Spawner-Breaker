# SpawnerBreaker

A Minecraft Bukkit/Spigot plugin that allows players to collect monster spawners with configurable permissions and rules.

## 📋 Description

SpawnerBreaker is a Minecraft server plugin that enables the collection of monster spawners while providing server administrators with full control over permissions and collection requirements. The plugin supports configurable permission systems, optional Silk Touch requirements, and prevents unauthorized spawner collection.

## ✨ Features

- **Permission-based spawner collection** - Configure which permission is required to collect spawners
- **Silk Touch toggle** - Optionally require Silk Touch enchantment to collect spawners
- **XP system** - Grant experience points when spawners are broken without proper permissions
- **Preserve spawner types** - Maintain the original spawner type (zombie, skeleton, etc.) when collected
- **Fully configurable** - All settings can be modified through commands or config file
- **Tab completion** - Full command tab completion support
- **Reliable event handling** - Proper event cancellation and XP management

## 🎮 Commands

| Command | Permission | Description |
|---------|------------|-------------|
| `/spawnerbreaker help` | `Spawner.Breaker.help` | Display help menu |
| `/spawnerbreaker reload` | `Spawner.Breaker.reload` | Reload configuration |
| `/spawnerbreaker setpermission <node>` | `Spawner.Breaker.setperm` | Set required permission |
| `/spawnerbreaker silktouch <enabled\|disabled>` | `Spawner.Breaker.silktouch` | Toggle Silk Touch requirement |

## ⚙️ Configuration

The plugin uses `config.yml` for all settings:

```yaml
prefix: "&7[&bSpawnerBreaker&7] "
no_permission_drop: "&cYou cannot collect this spawner!"
required_break_permission: "Spawner.Breaker.break"
silk_touch_required: false

messages:
  reloaded: "&aConfiguration reloaded!"
  help_header: "&b------ SpawnerBreaker Help ------"
  help_footer: "&b-------------------------------"
  setpermission_success: "&aRequired spawner break permission set to: &e%perm%"
  no_silk_touch: "&cYou need &eSilk Touch &cto collect this spawner!"
  silktouch_updated: "&aSilk Touch requirement is now: &e%state%"
```

### Configuration Options

- **`prefix`** - Chat prefix for all plugin messages
- **`no_permission_drop`** - Message when player lacks permission
- **`required_break_permission`** - Permission node required to collect spawners
- **`silk_touch_required`** - Whether Silk Touch is required (true/false)

## 🔧 Installation

1. Download the compiled `.jar` file
2. Place it in your server's `plugins` folder
3. Restart your server or reload plugins
4. The plugin will create the default configuration automatically

## 📦 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `Spawner.Breaker.break` | Required to collect spawners | op |
| `Spawner.Breaker.help` | Access to help command | true |
| `Spawner.Breaker.reload` | Access to reload command | op |
| `Spawner.Breaker.setperm` | Access to setpermission command | op |
| `Spawner.Breaker.silktouch` | Access to silktouch command | op |

## 🎯 How It Works

1. **No Permission**: Players without the required permission can break spawners but won't collect them. They receive 15 XP instead.
2. **Has Permission, No Silk Touch Required**: Players can collect spawners directly without any tool requirements.
3. **Has Permission, Silk Touch Required**: Players must have Silk Touch on their tool to collect spawners.

## 🔨 Developer Information

### Project Structure

```
src/main/java/org/ISoma05/spawnerBreaker/
├── SpawnerBreaker.java              # Main plugin class
├── commands/
│   ├── SpawnerBreakerCommand.java   # Command executor
│   └── SpawnerBreakerTab.java       # Tab completer
├── listeners/
│   └── SpawnerBreakerListener.java  # Event handlers
└── data/
    └── SpawnerBreakerManager.java   # Configuration manager
```

### API Requirements

- **Minecraft Version**: 1.21+
- **API**: Bukkit/Spigot
- **Java**: Compatible with Minecraft server requirements


## 🎛️ Usage Examples

### Setting Up Basic Spawner Collection

1. Grant players permission: `Spawner.Breaker.break`
2. Keep Silk Touch disabled for easy collection
3. Players can now collect spawners with any tool

### Advanced Setup with Silk Touch

1. Grant players permission: `Spawner.Breaker.break`
2. Enable Silk Touch requirement: `/spawnerbreaker silktouch enabled`
3. Players need Silk Touch enchanted tools to collect spawners

### Permission-Based Server

1. Create custom permission groups
2. Set specific permission: `/spawnerbreaker setpermission vip.spawner.collect`
3. Grant permission to desired user groups only

## 🐛 Troubleshooting

**Spawners not dropping when broken with permission:**
- Check if player has the configured permission
- Verify Silk Touch requirement is properly set
- Ensure no other plugins are interfering

**Commands not working:**
- Verify player has correct permissions
- Check console for error messages
- Reload plugin configuration

**XP not being granted:**
- Confirm players lack required permission
- Check if XP drops are enabled in server settings

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**VLX Soma** - *Initial work*

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! This is an open-source project aimed at improving the Minecraft server experience.

## 📞 Support

For support, feature requests, or bug reports, please create an issue in the project repository.

---

**Note**: This plugin is designed for Minecraft server administrators who want controlled spawner collection mechanics. Ensure all server rules comply with your server's gameplay policies.