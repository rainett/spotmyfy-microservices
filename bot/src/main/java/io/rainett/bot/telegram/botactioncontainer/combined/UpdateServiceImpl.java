package io.rainett.bot.telegram.botactioncontainer.combined;

import io.rainett.bot.telegram.botactioncontainer.enumerations.MessageType;
import io.rainett.bot.telegram.botactioncontainer.enumerations.UpdateType;
import io.rainett.bot.telegram.exception.UnknownMessageType;
import io.rainett.bot.telegram.exception.UnknownUpdateType;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class UpdateServiceImpl implements UpdateService {

    private static final Map<Predicate<Update>, UpdateType> UPDATE_TYPE_MAP = new HashMap<>();
    private static final Map<Predicate<Message>, MessageType> MESSAGE_TYPE_MAP = new HashMap<>();

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

        MESSAGE_TYPE_MAP.put(Message::hasText, MessageType.TEXT);
        MESSAGE_TYPE_MAP.put(Message::getDeleteChatPhoto, MessageType.DELETE_CHAT_PHOTO);
        MESSAGE_TYPE_MAP.put(Message::getGroupchatCreated, MessageType.GROUP_CHAT_CREATED);
        MESSAGE_TYPE_MAP.put(Message::getSuperGroupCreated, MessageType.SUPER_GROUP_CREATED);
        MESSAGE_TYPE_MAP.put(Message::getChannelChatCreated, MessageType.CHANNEL_CHAT_CREATED);
        MESSAGE_TYPE_MAP.put(m -> !m.getNewChatMembers().isEmpty(), MessageType.NEW_CHAT_MEMBERS);
        MESSAGE_TYPE_MAP.put(m -> m.getLeftChatMember() != null, MessageType.LEFT_CHAT_MEMBER);
        MESSAGE_TYPE_MAP.put(m -> m.getPinnedMessage() != null, MessageType.PINNED_MESSAGE);
        MESSAGE_TYPE_MAP.put(Message::hasPhoto, MessageType.PHOTO);
        MESSAGE_TYPE_MAP.put(m -> m.getNewChatPhoto() != null, MessageType.NEW_CHAT_PHOTO);
        MESSAGE_TYPE_MAP.put(Message::hasAudio, MessageType.AUDIO);
        MESSAGE_TYPE_MAP.put(Message::hasDocument, MessageType.DOCUMENT);
        MESSAGE_TYPE_MAP.put(Message::hasSticker, MessageType.STICKER);
        MESSAGE_TYPE_MAP.put(Message::hasVideo, MessageType.VIDEO);
        MESSAGE_TYPE_MAP.put(Message::hasContact, MessageType.CONTACT);
        MESSAGE_TYPE_MAP.put(Message::hasLocation, MessageType.LOCATION);
        MESSAGE_TYPE_MAP.put(m -> m.getVenue() != null, MessageType.VENUE);
        MESSAGE_TYPE_MAP.put(Message::hasAnimation, MessageType.ANIMATION);
        MESSAGE_TYPE_MAP.put(Message::hasVoice, MessageType.VOICE);
        MESSAGE_TYPE_MAP.put(m -> m.getGame() != null, MessageType.GAME);
        MESSAGE_TYPE_MAP.put(Message::hasInvoice, MessageType.INVOICE);
        MESSAGE_TYPE_MAP.put(Message::hasSuccessfulPayment, MessageType.SUCCESSFUL_PAYMENT);
        MESSAGE_TYPE_MAP.put(Message::hasVideoNote, MessageType.VIDEO_NOTE);
        MESSAGE_TYPE_MAP.put(Message::hasPassportData, MessageType.PASSPORT_DATA);
        MESSAGE_TYPE_MAP.put(Message::hasPoll, MessageType.POLL);
        MESSAGE_TYPE_MAP.put(Message::hasDice, MessageType.DICE);
        MESSAGE_TYPE_MAP.put(m -> m.getProximityAlertTriggered() != null, MessageType.PROXIMITY_ALERT_TRIGGERED);
        MESSAGE_TYPE_MAP.put(m -> m.getMessageAutoDeleteTimerChanged() != null, MessageType.MESSAGE_AUTO_DELETE_TIMER_CHANGED);
        MESSAGE_TYPE_MAP.put(m -> m.getWebAppData() != null, MessageType.WEB_APP_DATA);
        MESSAGE_TYPE_MAP.put(m -> m.getVideoChatStarted() != null, MessageType.VIDEO_CHAT_STARTED);
        MESSAGE_TYPE_MAP.put(m -> m.getVideoChatEnded() != null, MessageType.VIDEO_CHAT_ENDED);
        MESSAGE_TYPE_MAP.put(m -> m.getVideoChatParticipantsInvited() != null, MessageType.VIDEO_CHAT_PARTICIPANTS_INVITED);
        MESSAGE_TYPE_MAP.put(m -> m.getVideoChatScheduled() != null, MessageType.VIDEO_CHAT_SCHEDULED);
        MESSAGE_TYPE_MAP.put(m -> m.getForumTopicCreated() != null, MessageType.FORUM_TOPIC_CREATED);
        MESSAGE_TYPE_MAP.put(m -> m.getForumTopicClosed() != null, MessageType.FORUM_TOPIC_CLOSED);
        MESSAGE_TYPE_MAP.put(m -> m.getForumTopicReopened() != null, MessageType.FORUM_TOPIC_REOPENED);
        MESSAGE_TYPE_MAP.put(m -> m.getForumTopicEdited() != null, MessageType.FORUM_TOPIC_EDITED);
        MESSAGE_TYPE_MAP.put(m -> m.getGeneralForumTopicHidden() != null, MessageType.GENERAL_FORUM_TOPIC_HIDDEN);
        MESSAGE_TYPE_MAP.put(m -> m.getGeneralForumTopicUnhidden() != null, MessageType.GENERAL_FORUM_TOPIC_UNHIDDEN);
        MESSAGE_TYPE_MAP.put(m -> m.getWriteAccessAllowed() != null, MessageType.WRITE_ACCESS_ALLOWED);
        MESSAGE_TYPE_MAP.put(m -> m.getUserShared() != null, MessageType.USER_SHARED);
        MESSAGE_TYPE_MAP.put(m -> m.getChatShared() != null, MessageType.CHAT_SHARED);
        MESSAGE_TYPE_MAP.put(m -> m.getStory() != null, MessageType.STORY);
        MESSAGE_TYPE_MAP.put(Message::isCommand, MessageType.COMMAND);
    }


    @Override
    public Object getActionFromUpdate(Map<CombinedType, List<Object>> botActionsMap, Update update) {
        CombinedType combinedType = getCombinedTypeFromUpdate(update);
        List<Object> botActionsList = botActionsMap.get(combinedType);
        // select bot action from the list and return it
        throw new UnsupportedOperationException();
    }

    private CombinedType getCombinedTypeFromUpdate(Update update) {
        UpdateType updateType = getUpdateType(update);
        MessageType messageType = null;
        if (update.hasMessage()) {
            messageType = getMessageType(update.getMessage());
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
