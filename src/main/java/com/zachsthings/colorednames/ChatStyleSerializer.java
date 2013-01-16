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
