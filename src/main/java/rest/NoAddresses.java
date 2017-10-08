package rest;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "noAddresses", types = {Person.class})
public interface NoAddresses {

//    String getFirstName();

    String getLastName();
}
