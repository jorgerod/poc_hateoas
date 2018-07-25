package com.inditex.hateoas.component;

import com.inditex.hateoas.model.User;
import org.springframework.hateoas.SimpleIdentifiableResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class UserResourceAssembler extends SimpleIdentifiableResourceAssembler<User> {
    https://github.com/spring-projects/spring-hateoas-examples/blob/master/commons/src/main/java/org/springframework/hateoas/SimpleIdentifiableResourceAssembler.java
    EmployeeResourceAssembler() {
        super(EmployeeController.class);
    }

    /**
     * Define links to add to every {@link Resource}.
     *
     * @param resource
     */
    @Override
    protected void addLinks(Resource<Employee> resource) {

        /**
         * Add some custom links to the default ones provided.
         *
         * NOTE: To replace default links, don't invoke {@literal super.addLinks()}.
         */
        super.addLinks(resource);

        resource.getContent().getId()
                .ifPresent(id -> {
                    // Add additional links
                    resource.add(linkTo(methodOn(ManagerController.class).findManager(id)).withRel("manager"));
                    resource.add(linkTo(methodOn(EmployeeController.class).findDetailedEmployee(id)).withRel("detailed"));

                    // Maintain a legacy link to support older clients not yet adjusted to the switch from "supervisor" to "manager".
                    resource.add(linkTo(methodOn(SupervisorController.class).findOne(id)).withRel("supervisor"));
                });
    }

    /**
     * Define links to add to {@link Resources} collection.
     *
     * @param resources
     */
    @Override
    protected void addLinks(Resources<Resource<Employee>> resources) {

        super.addLinks(resources);

        resources.add(linkTo(methodOn(EmployeeController.class).findAllDetailedEmployees()).withRel("detailedEmployees"));
        resources.add(linkTo(methodOn(ManagerController.class).findAll()).withRel("managers"));
        resources.add(linkTo(methodOn(RootController.class).root()).withRel("root"));
    }
}
