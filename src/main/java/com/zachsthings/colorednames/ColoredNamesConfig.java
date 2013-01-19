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
