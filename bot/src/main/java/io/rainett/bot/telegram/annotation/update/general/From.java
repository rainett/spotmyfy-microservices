package io.rainett.bot.telegram.annotation.update.general;

public @interface From {

    /**
     * Array of ids'.
     * @return array of ids'
     */
    String[] value() default "";

}
