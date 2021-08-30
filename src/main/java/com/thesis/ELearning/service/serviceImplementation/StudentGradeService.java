package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.entity.API.ApiSettings;
import com.thesis.ELearning.entity.StudentGrade;
import com.thesis.ELearning.repository.StudentGradeRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceStudentGrade;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author Kurt Orioque
 */

@Transactional
@Service
@GraphQLApi
public class StudentGradeService implements PageableServiceStudentGrade {

    private final StudentGradeRepository repo;

    @Autowired
    public StudentGradeService(StudentGradeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentGrade> data(String search, int page, int status) {
        return null;
    }

    @Override
    public StudentGrade save(StudentGrade studentGrade) {
        repo.save(studentGrade);
        return studentGrade;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public StudentGrade findById(String id) {
        return null;
    }

    @Override
    public ApiSettings apiSettings() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    @GraphQLQuery(name = "getStudentGradeInClass")
    public StudentGrade studentGrade(@GraphQLArgument(name = "classId") String classId,
                                     @GraphQLArgument(name = "studentId") String studentId) {
        System.out.println("the classid " + classId);
        System.out.println("The studnet id " + studentId);
        return repo.getStudentGrade(classId,studentId);
    }
}
