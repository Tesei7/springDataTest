package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@RepositoryEventHandler(Person.class)
@Component
public class PersonEventHandler {
    private static final Logger log = LoggerFactory.getLogger(PersonEventHandler.class);

    @HandleBeforeCreate
    public void performSavePerson(Person person) {
        log.info("BeforeCreate: " + person);
    }
}
