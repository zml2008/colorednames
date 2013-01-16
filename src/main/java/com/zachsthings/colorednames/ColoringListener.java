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
