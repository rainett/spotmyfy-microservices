package io.rainett.bot.telegram.action.service.type;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.annotation.BotAction;
import io.rainett.bot.telegram.exception.UnknownUpdateType;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

@Service
public class TypeServiceImpl implements TypeService {

    private static final Map<Predicate<Update>, UpdateType> UPDATE_TYPE_MAP = new LinkedHashMap<>();

    static {
        UPDATE_TYPE_MAP.put(Update::hasChatJoinRequest, UpdateType.CHAT_JOIN_REQUEST);
        UPDATE_TYPE_MAP.put(Update::hasChatMember, UpdateType.CHAT_MEMBER);
        UPDATE_TYPE_MAP.put(Update::hasMyChatMember, UpdateType.MY_CHAT_MEMBER);
        UPDATE_TYPE_MAP.put(Update::hasPollAnswer, UpdateType.POLL_ANSWER);
        UPDATE_TYPE_MAP.put(Update::hasPoll, UpdateType.POLL_UPDATE);
        UPDATE_TYPE_MAP.put(Update::hasPreCheckoutQuery, UpdateType.PRE_CHECKOUT_QUERY);
        UPDATE_TYPE_MAP.put(Update::hasShippingQuery, UpdateType.SHIPPING_QUERY);
        UPDATE_TYPE_MAP.put(Update::hasCallbackQuery, UpdateType.CALLBACK_QUERY);
        UPDATE_TYPE_MAP.put(Update::hasChosenInlineQuery, UpdateType.CHOSEN_INLINE_QUERY);
        UPDATE_TYPE_MAP.put(Update::hasInlineQuery, UpdateType.INLINE_QUERY);
        UPDATE_TYPE_MAP.put(Update::hasEditedChannelPost, UpdateType.EDITED_CHANNEL_POST);
        UPDATE_TYPE_MAP.put(Update::hasChannelPost, UpdateType.CHANNEL_POST);
        UPDATE_TYPE_MAP.put(Update::hasEditedMessage, UpdateType.EDITED_MESSAGE);

        UPDATE_TYPE_MAP.put(u -> u.getMessage().isCommand(), UpdateType.COMMAND);
        UPDATE_TYPE_MAP.put(u -> u.getMessage().hasPoll(), UpdateType.POLL_MESSAGE);
        UPDATE_TYPE_MAP.put(u -> u.getMessage().hasText(), UpdateType.TEXT);
    }

    @Override
    public UpdateType getUpdateTypeFromUpdate(Update update) {
        return getUpdateType(update);
    }

    @Override
    public UpdateType getUpdateTypeFromAction(Object action) {
        return Objects.requireNonNull(AnnotationUtils
                .findAnnotation(action.getClass(), BotAction.class)).value();
    }

    private UpdateType getUpdateType(Update update) {
        for (Predicate<Update> predicate : getUpdateKeys()) {
            if (predicate.test(update)) {
                return getUpdateType(predicate);
            }
        }
        throw new UnknownUpdateType(update);
    }

    private Set<Predicate<Update>> getUpdateKeys() {
        return UPDATE_TYPE_MAP.keySet();
    }

    private UpdateType getUpdateType(Predicate<Update> predicate) {
        return UPDATE_TYPE_MAP.get(predicate);
    }

}
