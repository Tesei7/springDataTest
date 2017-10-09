package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class PersonCustomController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/people/search/findByName")
    public @ResponseBody ResponseEntity getByName(@RequestParam("name") String name){
        List<Person> people = repository.findByLastName(name);
        Resources<Person> resources = new Resources<Person>(people);

        resources.add(linkTo(methodOn(PersonCustomController.class).getByName(name)).withSelfRel());
        return ResponseEntity.ok(resources);
    }
}
