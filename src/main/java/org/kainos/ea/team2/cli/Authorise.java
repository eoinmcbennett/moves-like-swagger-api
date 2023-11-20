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
     * Stores the role that can access the resource.
     * By default, role is user.
     * @return
     */
    //UserRole value() default UserRole.User;

    /**
     * indicates if an admin role is required to visit a given route. Unless otherwise specified,
     * requireAdmin will be false.
     * @return
     */
    boolean requireAdmin() default false;
}
