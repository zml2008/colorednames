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
import org.spout.api.util.config.serialization.GenericType;
import org.spout.api.util.config.serialization.Serializer;

/**
 * Serialized for {@link ChatStyle ChatStyles}
 */
public class ChatStyleSerializer extends Serializer {
    @Override
    protected Object handleDeserialize(GenericType genericType, Object o) {
        if (o == null) {
            return null;
        }
        return ChatStyle.byName(o.toString());
    }

    @Override
    protected Object handleSerialize(GenericType genericType, Object o) {
        return ((ChatStyle) o).getLookupName();
    }

    @Override
    public boolean isApplicable(GenericType genericType) {
        return ChatStyle.class.isAssignableFrom(genericType.getMainType());
    }

    @Override
    protected int getParametersRequired() {
        return 0;
    }
}
