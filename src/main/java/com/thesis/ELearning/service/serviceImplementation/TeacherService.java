package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.repository.DashboardRepository;
import com.thesis.ELearning.repository.TeacherRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceTeacher;
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

@Transactional
@Service
@GraphQLApi
public class TeacherService implements PageableServiceTeacher {
    final private TeacherRepository repo;
    final private DashboardRepository dashboardRepository;
    final private DashBoard dashBoard;
    private int totalPages = 0;
    private long totalElements = 0;
    private int currentPages = 0;

    @Autowired
    public TeacherService(TeacherRepository repo, DashboardRepository dashboardRepository) {
        this.repo = repo;
        this.dashboardRepository = dashboardRepository;
        dashBoard = dashboardRepository.findById(1).orElse(null);
    }

    @Override
    public Teacher getTeacherById(String id) {
        return repo.teacher(id);
    }

    @Override
    @GraphQLQuery(name = "getTeacherAssignmentToGrade")
    public List<StudentAssignment> getTeacherAssignmentToGrade(@GraphQLArgument(name = "email") String email) {

        return repo.getTeacherAssignmentToGrade(email);
    }

    @Override
    @GraphQLQuery(name = "getTeacherExamToGrade")
    public List<StudentExam> getTeacherExamToGrade(@GraphQLArgument(name = "email") String email) {
        System.out.println("The email " + email
        );
        return repo.getTeacherExamToGrade(email);
    }

    @Override
    @GraphQLQuery(name = "getTeacherQuizToGrade")
    public List<StudentQuiz> getTeacherQuizToGrade(@GraphQLArgument(name = "email") String email) {
        return repo.getTeacherQuizToGrade(email);
    }

    @Override
    @GraphQLQuery(name = "getTeacherExamActive")
    public List<TeacherExams> getTeacherExam(@GraphQLArgument(name = "email") String email) {
        return repo.getTeacherExam(email);
    }

    @Override
    @GraphQLQuery(name = "getTeacherAssignmentActive")
    public List<TeacherAssignment> getTeacherAssignment(@GraphQLArgument(name = "email") String email) {
        return repo.getTeacherAssignment(email);
    }

    @Override
    @GraphQLQuery(name = "getTeacherQuizzesActive")
    public List<TeacherQuizzes> getTeacherQuizzes(@GraphQLArgument(name = "email") String email) {
        return repo.getTeacherQuizzes(email);
    }

    @Override
    @GraphQLQuery(name = "getTeacherResources")
    public List<TeacherResources> getTeacherResources(@GraphQLArgument(name = "search") String search,
                                                      @GraphQLArgument(name = "email") String email,
                                                      @GraphQLArgument(name = "page") int page,
                                                      @GraphQLArgument(name = "status") int status) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<TeacherResources> pages;
        if (status == 2) pages = repo.getTeacherResources(search, email, pageable);
        else pages = repo.getTeacherResources(search, email, status, pageable);
        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    @GraphQLQuery(name = "teachers")
    public List<Teacher> data(@GraphQLArgument(name = "search") String search,
                              @GraphQLArgument(name = "page") int page,
                              @GraphQLArgument(name = "status") int status) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Teacher> pages;

        if (status == 2) pages = repo.Teachers(search, pageable);
        else pages = repo.Teachers(search, status, pageable);

        totalElements = pages.getTotalElements();
        totalPages = pages.getTotalPages();
        currentPages = page;
        return pages.getContent();
    }

    @Override
    public Teacher save(Teacher teacher) {
        try {
            if (repo.findById(teacher.getId()).isEmpty()) {
                dashboardRepository.save(dashBoard.IncTeacherCount());
            }
            repo.save(teacher);
        } catch (Exception e) {
            return null;
        }
        return teacher;
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        try {
            repo.deleteById(id);
            dashboardRepository.save(dashBoard.DecTeacherCount());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @GraphQLQuery(name = "getTeacherByUserEmail")
    public Teacher findById(@GraphQLArgument(name = "email") String email) {

        return repo.getTeacherByUserEmail(email);
    }

    @GraphQLQuery(name = "getTeacherClasses")
    public List<RoomShiftClass> getTeacherRoomClass(@GraphQLArgument(name = "teacherId") String teacherId,
                                                    @GraphQLArgument(name = "status") int status) {

        return repo.getTeacherClass(teacherId, status);
    }

    @Override
    @GraphQLQuery(name = "teacherSettings")
    public ApiSettings apiSettings() {
        return new ApiSettings(totalElements, totalPages, currentPages);
    }

    @Override
    public long count() {
        return repo.count();
    }
}
