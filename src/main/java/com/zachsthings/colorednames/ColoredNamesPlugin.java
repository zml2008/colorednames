package com.zachsthings.colorednames;

import org.spout.api.chat.style.ChatStyle;
import org.spout.api.chat.style.ColorChatStyle;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.util.config.serialization.Serialization;
import org.spout.api.util.config.yaml.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Main plugin class
 */
public class ColoredNamesPlugin extends CommonPlugin {
    static {
        Serialization.registerSerializer(new ChatStyleSerializer());
    }

    private ColoredNamesConfig config;
    private List<ChatStyle> colorChatStyles = new ArrayList<ChatStyle>();

    @Override
    public void onEnable() {
        config = new ColoredNamesConfig(new YamlConfiguration(new File(getDataFolder(), "config.yml")));
        try {
            config.load();
            config.save();
        } catch (ConfigurationException e) {
            getLogger().log(Level.WARNING, "Error loading configuration", e);
        }

        for (ChatStyle style : ChatStyle.getValues()) {
            if (style instanceof ColorChatStyle) {
                colorChatStyles.add(style);
            }
        }
        colorChatStyles.removeAll(config.getUnusedColors());

        getEngine().getEventManager().registerEvents(new ColoringListener(this), this);
        getLogger().info("Successfully enabled");
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onReload() {
        try {
            config.load();
        } catch (ConfigurationException e) {
            getLogger().log(Level.WARNING, "Error loading configuration", e);
        }


        List<ChatStyle> colorChatStyles = new ArrayList<ChatStyle>();
        for (ChatStyle style : ChatStyle.getValues()) {
            if (style instanceof ColorChatStyle) {
                colorChatStyles.add(style);
            }
        }
        colorChatStyles.removeAll(config.getUnusedColors());
        this.colorChatStyles = colorChatStyles;
    }

    public ColoredNamesConfig getConfig() {
        return config;
    }

    public ChatStyle getStyle(String playerName) {
        return colorChatStyles.get(Math.abs(playerName.hashCode()) % colorChatStyles.size());
    }
}
