package com.inditex.hateoas.component;

import com.inditex.hateoas.controller.RootController;
import com.inditex.hateoas.controller.UserController;
import com.inditex.hateoas.model.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }


//    https://github.com/spring-projects/spring-hateoas-examples/blob/master/commons/src/main/java/org/springframework/hateoas/SimpleIdentifiableResourceAssembler.java

//
//    protected void addLinks(Resource<User> resource) {
//
//        /**
//         * Add some custom links to the default ones provided.
//         *
//         * NOTE: To replace default links, don't invoke {@literal super.addLinks()}.
//         */
//        super.addLinks(resource);
//
//        resource.getContent().getId()
//                .ifPresent(id -> {
//                    // Add additional links
//                    resource.add(linkTo(methodOn(ManagerController.class).findManager(id)).withRel("manager"));
//                    resource.add(linkTo(methodOn(EmployeeController.class).findDetailedEmployee(id)).withRel("detailed"));
//
//                    // Maintain a legacy link to support older clients not yet adjusted to the switch from "supervisor" to "manager".
//                    resource.add(linkTo(methodOn(SupervisorController.class).findOne(id)).withRel("supervisor"));
//                });
//    }
//
//
//    protected void addLinks(Resources<Resource<Employee>> resources) {
//
//        super.addLinks(resources);
//
//        resources.add(linkTo(methodOn(EmployeeController.class).findAllDetailedEmployees()).withRel("detailedEmployees"));
//        resources.add(linkTo(methodOn(ManagerController.class).findAll()).withRel("managers"));
//        resources.add(linkTo(methodOn(RootController.class).root()).withRel("root"));
//    }

    @Override
    public UserResource toResource(User entity) {
        Link linkRoot = linkTo(methodOn(RootController.class).root()).withRel("root");
        Link linkSelf = linkTo(methodOn(UserController.class).getUserById(entity.getId())).withSelfRel();
        Link linkOrders = linkTo(methodOn(UserController.class).getOrdersForUser(entity.getId())).withRel("orders");
        UserResource resource = new UserResource(entity, linkSelf, linkOrders, linkRoot);
        return resource;
    }
}
