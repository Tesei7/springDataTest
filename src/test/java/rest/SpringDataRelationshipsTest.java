package rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringDataRelationshipsTest {

    @Autowired
    private TestRestTemplate template;

    private static String BOOK_ENDPOINT = "http://localhost:8080/books/";
    private static String AUTHOR_ENDPOINT = "http://localhost:8080/authors/";
    private static String ADDRESS_ENDPOINT = "http://localhost:8080/addresses/";
    private static String PERSON_ENDPOINT = "http://localhost:8080/people/";

    private static String PERSON_FIRST_NAME = "Ilia";
    private static String PERSON_LAST_NAME = "Bochkarev";
    private static String AUTHOR_NAME = "George Orwell";

    @Test
    public void whenSaveOneToOneRelationship_thenCorrect() {
        //given
        Person person = new Person(PERSON_FIRST_NAME, PERSON_LAST_NAME);
        template.postForEntity(PERSON_ENDPOINT, person, Person.class);
        Address address = new Address("USA", "LA");
        template.postForEntity(ADDRESS_ENDPOINT, address, Address.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-type", "text/uri-list");
        HttpEntity<String> httpEntity = new HttpEntity<>(ADDRESS_ENDPOINT + "1", requestHeaders);
        template.exchange(PERSON_ENDPOINT + "1/personAddress", HttpMethod.PUT, httpEntity, String.class);
        //when
        ResponseEntity<Address> addressResponseEntity = template.getForEntity(PERSON_ENDPOINT + "1/personAddress", Address.class);
        //then
        assertEquals("address is incorrect", addressResponseEntity.getBody().getCountry(), "USA");
    }
}
