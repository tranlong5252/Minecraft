package net.minevn.tranlong5252;

import net.minevn.tranlong5252.commands.BruhCommand;
import net.minevn.tranlong5252.commands.GetLocCommand;
import net.minevn.tranlong5252.events.RideListener;
import net.minevn.tranlong5252.events.TridentListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public final class Main extends JavaPlugin {

    public static final String PREFIX = "§cUtility §8» §7";
    public static Main plugin;
    private File configFile;
    private YamlConfiguration config;
    public HashMap<String, Long> cooldown = new HashMap<>();
    public int cooldownTime;

    @Override
    public void onEnable() {
        plugin = this;
        this.configFile = getConfig("config", this);
        this.reload();
        if (!this.config.isSet("cooldown-command")) {
            this.config.set("cooldown-command", 10);
            this.save();
        }
        cooldownTime = this.config.getInt("cooldown-command");

        Objects.requireNonNull(getCommand("bruh")).setExecutor(new BruhCommand(this));
        Objects.requireNonNull(getCommand("gl+")).setExecutor(new GetLocCommand());
        getServer().getPluginManager().registerEvents(new RideListener(), this);
        getServer().getPluginManager().registerEvents(new TridentListener(), this);;
        getLogger().info(PREFIX + "Plugin đang bật");

    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    }
    public void save() {
        try {
            this.config.save(this.configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info(PREFIX + "Plugin đã được tắt");
    }

    public static File getConfig(final String name, final Plugin plugin) {
        final File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        final File configFile = new File(plugin.getDataFolder() + File.separator + name + ".yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return configFile;
    }
}