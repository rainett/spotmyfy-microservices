package io.rainett.bot.telegram.botactioncontainer.combined;

import io.rainett.bot.telegram.action.DefaultAction;
import io.rainett.bot.telegram.botactioncontainer.enumerations.MessageType;
import io.rainett.bot.telegram.botactioncontainer.enumerations.UpdateType;
import io.rainett.bot.telegram.exception.UnknownMessageType;
import io.rainett.bot.telegram.exception.UnknownUpdateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class BotActionServiceImpl implements BotActionService {

    private static final Map<Predicate<Update>, UpdateType> UPDATE_TYPE_MAP = new LinkedHashMap<>();
    private static final Map<Predicate<Message>, MessageType> MESSAGE_TYPE_MAP = new LinkedHashMap<>();
    private static final Map<UpdateType, Function<Update, Message>> UPDATES_MESSAGE_MAP = new HashMap<>();

    static {
        UPDATE_TYPE_MAP.put(Update::hasChatJoinRequest, UpdateType.CHAT_JOIN_REQUEST);
        UPDATE_TYPE_MAP.put(Update::hasChatMember, UpdateType.CHAT_MEMBER);
        UPDATE_TYPE_MAP.put(Update::hasMyChatMember, UpdateType.MY_CHAT_MEMBER);
        UPDATE_TYPE_MAP.put(Update::hasPollAnswer, UpdateType.POLL_ANSWER);
        UPDATE_TYPE_MAP.put(Update::hasPoll, UpdateType.POLL);
        UPDATE_TYPE_MAP.put(Update::hasPreCheckoutQuery, UpdateType.PRE_CHECKOUT_QUERY);
        UPDATE_TYPE_MAP.put(Update::hasShippingQuery, UpdateType.SHIPPING_QUERY);
        UPDATE_TYPE_MAP.put(Update::hasCallbackQuery, UpdateType.CALLBACK_QUERY);
        UPDATE_TYPE_MAP.put(Update::hasChosenInlineQuery, UpdateType.CHOSEN_INLINE_QUERY);
        UPDATE_TYPE_MAP.put(Update::hasInlineQuery, UpdateType.INLINE_QUERY);
        UPDATE_TYPE_MAP.put(Update::hasEditedChannelPost, UpdateType.EDITED_CHANNEL_POST);
        UPDATE_TYPE_MAP.put(Update::hasChannelPost, UpdateType.CHANNEL_POST);
        UPDATE_TYPE_MAP.put(Update::hasEditedMessage, UpdateType.EDITED_MESSAGE);
        UPDATE_TYPE_MAP.put(Update::hasMessage, UpdateType.MESSAGE);

        MESSAGE_TYPE_MAP.put(Message::isCommand, MessageType.COMMAND);
        MESSAGE_TYPE_MAP.put(Message::hasText, MessageType.TEXT);

        UPDATES_MESSAGE_MAP.put(UpdateType.MESSAGE, Update::getMessage);
        UPDATES_MESSAGE_MAP.put(UpdateType.EDITED_MESSAGE, Update::getEditedMessage);
        UPDATES_MESSAGE_MAP.put(UpdateType.CHANNEL_POST, Update::getChannelPost);
        UPDATES_MESSAGE_MAP.put(UpdateType.EDITED_CHANNEL_POST, Update::getEditedChannelPost);
    }

    private final DefaultAction defaultAction;


    @Override
    public Object getActionFromUpdate(Map<CombinedType, List<Object>> botActionsMap, Update update) {
        CombinedType combinedType = getCombinedTypeFromUpdate(update);
        List<Object> botActionsList = botActionsMap.get(combinedType);
        return botActionsList == null || botActionsList.isEmpty() ? defaultAction : botActionsList.get(0);
    }

    private CombinedType getCombinedTypeFromUpdate(Update update) {
        UpdateType updateType = getUpdateType(update);
        MessageType messageType = null;
        Message message = UPDATES_MESSAGE_MAP.get(updateType).apply(update);
        if (message != null) {
            messageType = getMessageType(message);
        }
        return new CombinedType(updateType, messageType);
    }

    private UpdateType getUpdateType(Update update) {
        for (Predicate<Update> predicate : UPDATE_TYPE_MAP.keySet()) {
            if (predicate.test(update)) {
                return UPDATE_TYPE_MAP.get(predicate);
            }
        }
        throw new UnknownUpdateType(update);
    }

    private MessageType getMessageType(Message message) {
        for (Predicate<Message> predicate : MESSAGE_TYPE_MAP.keySet()) {
            if (predicate.test(message)) {
                return MESSAGE_TYPE_MAP.get(predicate);
            }
        }
        throw new UnknownMessageType(message);
    }


}
