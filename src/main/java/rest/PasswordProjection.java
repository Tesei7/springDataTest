package rest;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "passwords", types = {Person.class})
public interface PasswordProjection {

    String getPassword();
}
