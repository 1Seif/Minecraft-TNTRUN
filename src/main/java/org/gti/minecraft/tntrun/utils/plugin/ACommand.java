package org.gti.minecraft.tntrun.utils.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ACommand {
    
    String name();
    String permission() default "";
    boolean requiresPlayer() default true;
    
}
