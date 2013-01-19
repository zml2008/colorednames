/**
 * ColoredNames
 * Copyright (C) 2013 zml2008 <zach@zachsthings.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
