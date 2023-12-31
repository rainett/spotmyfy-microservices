package io.rainett.bot.actions.inline;

import io.rainett.bot.telegram.Bot;
import io.rainett.bot.telegram.annotation.update.InlineQuery;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultPhoto;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@InlineQuery
@RequiredArgsConstructor
public class CatAction {

    private final Bot bot;

    private static int counter = 0;
    private static final String CAT_URL_FORMAT = "https://http.cat/images/%s.jpg";

    private static final Map<String, InlineQueryResultPhoto> cats = new LinkedHashMap<>();

    static {
        for (HttpStatus status : HttpStatus.values()) {
            String code = String.valueOf(status.value());
            String photoUrl = String.format(CAT_URL_FORMAT, code);
            if (isUrlValid(photoUrl)) {
                cats.put(code, getCatResultPhoto(photoUrl));
            }
        }
    }

    @SneakyThrows
    public void handleUpdate(Update update) {
        if (!update.hasInlineQuery()) {
            return;
        }
        String queryId = update.getInlineQuery().getId();
        String query = update.getInlineQuery().getQuery();
        List<InlineQueryResult> results = new LinkedList<>();
        if (cats.containsKey(query)) {
            results.add(cats.get(query));
        } else {
            results.addAll(cats.values().stream().limit(50).toList());
        }
        bot.execute(new AnswerInlineQuery(queryId, results));
    }

    private static InlineQueryResultPhoto getCatResultPhoto(String photoUrl) {
        InlineQueryResultPhoto resultPhoto =
                new InlineQueryResultPhoto(String.valueOf(counter++), photoUrl);
        resultPhoto.setThumbnailUrl(photoUrl);
        return resultPhoto;
    }

    private static boolean isUrlValid(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            return false;
        }
    }



}
