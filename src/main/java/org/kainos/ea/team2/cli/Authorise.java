package org.kainos.ea.team2.cli;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows endpoints to mark what roles can access them.
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
@NameBinding
public @interface Authorise {
    /**
     * Stores the passed in role for the annotation.
     * @return the lowest role that can access the resource
     */
    UserRole value() default UserRole.User;
}
