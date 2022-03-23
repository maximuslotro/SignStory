package com.maximuslotro.mc.signpic;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(value={METHOD})
@Retention(value=RUNTIME)
public @interface CoreEvent {
}
