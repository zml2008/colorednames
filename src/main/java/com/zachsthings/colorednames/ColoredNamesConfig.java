package com.zachsthings.colorednames;

import org.spout.api.chat.style.ChatStyle;
import org.spout.api.util.config.Configuration;
import org.spout.api.util.config.annotated.AnnotatedSubclassConfiguration;
import org.spout.api.util.config.annotated.Setting;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration for colored names
 */
public class ColoredNamesConfig extends AnnotatedSubclassConfiguration {
    // Colors that should not be used to color names
    @Setting("unused-colors") private Set<ChatStyle> unusedColors =
            new HashSet<ChatStyle>(Arrays.asList(ChatStyle.DARK_GRAY, ChatStyle.BLACK));

    public ColoredNamesConfig(Configuration baseConfig) {
        super(baseConfig);
    }

    public Set<ChatStyle> getUnusedColors() {
        return Collections.unmodifiableSet(unusedColors);
    }
}
