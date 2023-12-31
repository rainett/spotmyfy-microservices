package io.rainett.bot.telegram.action.service.comparator;


import io.rainett.bot.telegram.action.service.comparator.service.ComparatorServiceImpl;
import io.rainett.bot.telegram.annotation.ActionComparator;
import io.rainett.bot.telegram.annotation.update.message.Command;

import java.util.Comparator;

@ActionComparator(Command.class)
public class CommandComparator implements Comparator<Command> {

    /**
     * Compares two commands. Longest should go first, considering that {@link ComparatorServiceImpl} is using {@link Comparator#reversed()}.
     * @param c1 the first object to be compared.
     * @param c2 the second object to be compared.
     * @return comparison result
     */
    @Override
    public int compare(Command c1, Command c2) {
        return Integer.compare(c1.value().length(), c2.value().length());
    }
}
