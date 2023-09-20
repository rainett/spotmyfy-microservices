package io.rainett.bot.telegram.botactioncontainer.combined;

import io.rainett.bot.telegram.botactioncontainer.enumerations.MessageType;
import io.rainett.bot.telegram.botactioncontainer.enumerations.UpdateType;

public record CombinedType(UpdateType updateType, MessageType messageType) {}
