package rest;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineAddress", types = {Person.class})
public interface InlineAddress {
    String getFirstName();

    String getLastName();

    Address getAddress();
}
