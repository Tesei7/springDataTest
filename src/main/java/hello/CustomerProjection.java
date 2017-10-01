package hello;

import java.util.List;

public interface CustomerProjection {
    String getFirstName();
    String getLastName();

    default String getFullName() {
        return getFirstName().concat(" ").concat(getLastName());
    }

    List<AddressCountry> getAddresses();

    interface AddressCountry {
        String getCity();
    }
}
