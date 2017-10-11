package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

@SpringBootApplication
@Import(RepositoryRestMvcConfiguration.class)
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    public ResourceProcessor<Resource<Person>> personProcessor() {
        return new ResourceProcessor<Resource<Person>>() {
            @Override
            public Resource<Person> process(Resource<Person> resource) {
                resource.add(new Link("http://localhost:8080/people", "added-link"));
                return resource;
            }
        };
    }


}
