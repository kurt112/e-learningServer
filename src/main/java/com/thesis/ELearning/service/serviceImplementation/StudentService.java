package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.repository.DashboardRepository;
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
import java.util.*;

@Transactional
@Service
@GraphQLApi
public class StudentService implements PageableServiceStudent {

    final private StudentRepository repo;
    final private DashboardRepository dashboardRepository;
    final private DashBoard dashBoard;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;


    @Autowired
    public StudentService(StudentRepository repo, DashboardRepository dashboardRepository) {
        this.repo = repo;
        this.dashboardRepository = dashboardRepository;
        dashBoard = dashboardRepository.findById(1).orElse(null);
    }


    @Override
    @GraphQLQuery(name = "students")
    public List<Student> data(@GraphQLArgument(name = "search") String search,
                              @GraphQLArgument(name = "page") int page,
                              @GraphQLArgument(name= "status") int status) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> pages;

        if(status ==2) pages =repo.Students(search, pageable);
        else pages = repo.Students(search,status,pageable);

        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;


        return pages.getContent();
    }

    @Override
    @GraphQLQuery(name = "studentSave")
    public Student save(@GraphQLArgument(name = "SaveStudent") Student student) {
        try {
            if (repo.findById(student.getId()).isEmpty()) {
                dashboardRepository.save(dashBoard.IncStudentCount());
            }

            repo.save(student);
        } catch (Exception e) {
            return null;
        }
        return student;
    }

    @Override
    public boolean deleteById(String id) {
        try {
            repo.deleteById(id);
            dashboardRepository.save(dashBoard.DecStudentCount());
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    @GraphQLQuery(name = "getStudentByUserEmail")
    public Student findById(@GraphQLArgument(name = "email") String email) {


        return repo.getStudentByUserEmail(email);
    }


    @Override
    public Student getStudentById(String id) {
        Optional<Student> student = repo.findById(id);
        return student.orElse(null);
    }

    @Override
    @GraphQLQuery(name = "getStudentClass")
    public List<RoomShiftClass> getStudentClass(@GraphQLArgument(name = "email") String email,
                                                @GraphQLArgument(name = "status") int status) {

        System.out.println(email);
        System.out.println(status);
        return repo.roomShiftClass(email,status);
    }

    @Override
    @GraphQLQuery(name = "getStudentAssignment")
    public List<StudentAssignment> getStudentAssignment(@GraphQLArgument(name = "email") String email) {
        return repo.getStudentAssignment(email);
    }

    @Override
    @GraphQLQuery(name = "getStudentAssignmentArchive")
    public List<StudentAssignment> getStudentArchiveAssignment(@GraphQLArgument(name = "email") String email,
                                                               @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page, 15);

        return repo.getStudentAssignmentArchive(email, pageable).getContent();
    }

    @Override
    @GraphQLQuery(name = "getStudentExam")
    public List<StudentExam> getStudentExam(@GraphQLArgument(name = "email") String email) {
        return repo.getStudentExam(email);
    }

    @Override
    @GraphQLQuery(name = "getStudentExamArchive")
    public List<StudentExam> getStudentExamArchive(@GraphQLArgument(name = "email") String email,
                                                   @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page, 15);

        return repo.getStudentExamArchive(email,pageable).getContent();
    }

    @Override
    @GraphQLQuery(name = "getStudentQuiz")
    public List<StudentQuiz> getStudentQuiz(@GraphQLArgument(name = "email") String email) {
        return repo.getStudentQuiz(email);
    }

    @Override
    @GraphQLQuery(name = "getStudentQuizArchive")
    public List<StudentQuiz> getStudentQuizArchive(@GraphQLArgument(name = "email") String email,
                                                   @GraphQLArgument(name = "page") int page) {
        Pageable pageable = PageRequest.of(page, 15);
        return repo.getStudentQuizArchive(email,pageable).getContent();
    }


    @Override
    public long count() {
        return repo.count();
    }

    @Override
    @GraphQLQuery(name = "studentSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }

    @GraphQLQuery(name = "getRoomShiftByStudent")
    public Set<RoomShift> StudentTransfer(@GraphQLArgument(name = "studentID") String id) {

        Student student = findById(id);

        return student != null ? student.getRoomShifts() : new HashSet<>();
    }

}
