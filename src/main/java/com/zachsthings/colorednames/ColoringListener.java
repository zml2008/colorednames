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

import org.spout.api.chat.ChatArguments;
import org.spout.api.chat.Placeholder;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerChatEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Listener for colored names
 */
public class ColoringListener implements Listener {
    private final ColoredNamesPlugin plugin;

    public ColoringListener(ColoredNamesPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        if (!event.getPlayer().hasPermission("colorednames.color")) {
            return;
        }

        List<Object> argElements = new ArrayList<Object>(event.getFormat().getArguments().getArguments());
        List<ChatStyle> preChatStyles = new ArrayList<ChatStyle>();
        preChatStyles.add(ChatStyle.RESET);
        int colorInsertIndex = -1;
        for (int i = 0; i < argElements.size(); ++i) {
            if (argElements.get(i) instanceof Placeholder
                    && argElements.get(i).equals(PlayerChatEvent.NAME)) {
                colorInsertIndex = i;
                break;
            } else if (argElements.get(i) instanceof ChatStyle) {
                for (Iterator<ChatStyle> it = preChatStyles.iterator(); it.hasNext();) {
                    if (((ChatStyle) argElements.get(i)).conflictsWith(it.next())) {
                        it.remove();
                    }
                }
                preChatStyles.add((ChatStyle) argElements.get(i));
            }
        }

        if (colorInsertIndex == -1) {
            return;
        }

        argElements.add(colorInsertIndex, plugin.getStyle(event.getPlayer().getDisplayName()));
        argElements.addAll(colorInsertIndex + 2, preChatStyles);
        event.setFormat(new ChatArguments(argElements));
    }
}
