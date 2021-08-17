package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.User;
import com.thesis.ELearning.repository.UserRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceUser;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@GraphQLApi
public class UserService implements PageableServiceUser {
    final private UserRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;
    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }


    @GraphQLQuery(name = "getUsersWithRole")
    public List<User> data(@GraphQLArgument(name = "search")String search,
                            @GraphQLArgument(name = "page") int page,
                           @GraphQLArgument(name = "role") String role ) {
        Pageable pageable = PageRequest.of(page,10);
        Page<User> pages =  repo.usersWitRole(search,role, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public List<User> data(@GraphQLArgument(name = "search")String search,
                           @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<User> pages =  repo.findAll(pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }



    @Override
    @GraphQLQuery(name = "saveUser")
    public User save(User user) {
        try {
            repo.save(user);
        }catch (Exception e){
            return null;
        }
        return user;
    }

    @Override
    @GraphQLQuery(name = "deleteUserById")
    public boolean deleteById(String id) {
        try {
            repo.deleteById(Integer.parseInt(id));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public User findById(@GraphQLArgument(name = "id") String id) {
        System.out.println(id);
        Optional<User> user = repo.findById(Integer.parseInt(id));
        return user.orElse(null);
    }

    @Override
    public long count() {
        return repo.count();
    }

    @GraphQLQuery(name = "user")
    @Override
    public User findByEmail(@GraphQLArgument(name = "email")String email){
        return repo.findUserEmail(email);
    }

    @GraphQLQuery(name = "userSettings")
    @Override
    public ApiSettings apiSettings() {
         return new ApiSettings(totalElements, totalPages, currentPages);
    }
}
