package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.RoomShift;
import com.thesis.ELearning.entity.RoomShiftClass;
import com.thesis.ELearning.entity.Student;
import com.thesis.ELearning.repository.StudentRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceStudent;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@GraphQLApi
public class StudentService implements PageableServiceStudent {

    final private StudentRepository repo;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }


    @Override
    @GraphQLQuery(name = "students")
    public List<Student> data(@GraphQLArgument(name = "search")String search,
                              @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> pages = repo.Students(search, pageable);
        totalElements =  pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;


        return pages.getContent();
    }




    @Override
    @GraphQLQuery(name = "studentSave")
    public Student save(@GraphQLArgument(name = "SaveStudent") Student student) {
        try {
            repo.save(student);
        }catch (Exception e){
            return null;
        }
        return student;
    }

    @Override
    @GraphQLQuery(name = "RemoveStudent")
    public boolean deleteById(@GraphQLArgument(name = "id") String id) {
        try {
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    @GraphQLQuery(name = "student")
    public Student findById(@GraphQLArgument(name = "id") String id) {
        Optional<Student> student = repo.findById(id);

        return student.orElse(null);
    }


    @Override
    @GraphQLQuery(name = "studentSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }

    @GraphQLQuery(name = "getRoomShiftByStudent")
    public List<RoomShift> StudentTransfer(@GraphQLArgument(name = "studentID")String id) {

        Student student = findById(id);

        return student!=null? student.getRoomShifts(): new ArrayList<>();
    }

    @GraphQLQuery(name = "getStudentByUserID")
    public Student getStudentByUserId(@GraphQLArgument(name = "userID") String id){

        return repo.getStudentByUserId(id);
    }



}
