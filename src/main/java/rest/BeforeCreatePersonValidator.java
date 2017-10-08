package rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service("beforeCreatePersonValidator")
public class BeforeCreatePersonValidator implements Validator {
    @Value("${error.empty.value}")
    private String shouldNotBeEmptyMessage;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Person user = (Person) obj;
        if (checkInputString(user.getFirstName())) {
            errors.rejectValue("firstName", "first.name.empty", shouldNotBeEmptyMessage);
        }

        if (checkInputString(user.getLastName())) {
            errors.rejectValue("lastName", "last.name.empty", shouldNotBeEmptyMessage);
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
