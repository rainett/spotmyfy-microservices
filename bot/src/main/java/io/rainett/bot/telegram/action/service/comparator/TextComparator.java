package io.rainett.bot.telegram.action.service.comparator;


import io.rainett.bot.telegram.annotation.ActionComparator;
import io.rainett.bot.telegram.annotation.update.message.Text;

import java.util.Comparator;

@ActionComparator(Text.class)
public class TextComparator implements Comparator<Text> {
    @Override
    public int compare(Text t1, Text t2) {
        // compare by equals
        if (!t1.equals().isEmpty() && !t2.equals().isEmpty()) {
            return t1.equals().compareTo(t2.equals());
        }
        if (!t1.equals().isEmpty()) {
            return 1;
        }
        if (!t2.equals().isEmpty()) {
            return -1;
        }

        // compare by the others
        int t1sW = t1.startsWith().length();
        int t1eW = t1.endsWith().length();
        int t1c = t1.contains().length();
        int t1Value = t1sW + t1eW + t1c;

        int t2sW = t2.startsWith().length();
        int t2eW = t2.endsWith().length();
        int t2c = t2.contains().length();
        int t2Value = t2sW + t2eW + t2c;

        return Integer.compare(t1Value, t2Value);
    }
}
