package rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "noAddresses", types = {Person.class})
public interface NoAddresses {

    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();
}
