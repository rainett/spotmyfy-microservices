package io.rainett.bot.telegram.action.service.comparator.service;

import io.rainett.bot.telegram.annotation.ActionComparator;
import io.rainett.bot.telegram.annotation.BotAction;
import io.rainett.bot.telegram.annotation.update.message.Text;
import io.rainett.bot.telegram.exception.ActionAnnotationNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ComparatorServiceImpl implements ComparatorService {

    private final Map<Class<? extends Annotation>, Comparator<Object>> comparatorMap;

    @SuppressWarnings("unchecked")
    public ComparatorServiceImpl(ApplicationContext context) {
        this.comparatorMap = context.getBeansWithAnnotation(ActionComparator.class).values().stream()
                .map(b -> (Comparator<Object>) b)
                .collect(Collectors.toMap(ComparatorServiceImpl::getComparableAction, Function.identity()));
    }

    private static Class<? extends Annotation> getComparableAction(Object bean) {
        return bean.getClass().getDeclaredAnnotation(ActionComparator.class).value();
    }

    @Override
    public int compare(Object o1, Object o2) {
        log.info("Comparing {} to {}", o1.getClass().getSimpleName(), o2.getClass().getSimpleName());
        log.info("Contains {} vs {}", o1.getClass().getDeclaredAnnotation(Text.class).contains(), o2.getClass().getDeclaredAnnotation(Text.class).contains());
        Class<? extends Annotation> annotationType = getBotActionAnnotationType(o1);
        int compare = Optional.ofNullable(comparatorMap
                        .get(annotationType))
                .orElse(Comparator.comparingInt(a -> 1))
                .reversed()
                .compare(getAnnotation(o1, annotationType), getAnnotation(o2, annotationType));
        log.info("{} {} {}", o1.getClass().getSimpleName(), compare > 0 ? ">" : compare < 0 ? "<" : "=", o2.getClass().getSimpleName());
        return compare;
    }

    private static Annotation getAnnotation(Object o, Class<? extends Annotation> annotationType) {
        return o.getClass().getDeclaredAnnotation(annotationType);
    }

    private static Class<? extends Annotation> getBotActionAnnotationType(Object o) {
        for (Annotation annotation : o.getClass().getDeclaredAnnotations()) {
            if (annotation.annotationType().isAnnotationPresent(BotAction.class)) {
                return annotation.annotationType();
            }
        }
        throw new ActionAnnotationNotFound(o.getClass());
    }

}
