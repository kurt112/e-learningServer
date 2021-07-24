package com.thesis.ELearning.service.PageableService;

import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.service.PagableParentClass.ServicesGraphQl;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Service;

@Service
public interface PageableServiceUser extends ServicesGraphQl<User> {

    User findByEmail(String email);
}
