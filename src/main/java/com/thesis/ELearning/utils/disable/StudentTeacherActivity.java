
package com.thesis.ELearning.utils.disable;

import com.thesis.ELearning.entity.*;
import com.thesis.ELearning.service.serviceImplementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

/**
 * @Author Kurt Orioque
 */

@Service
public class StudentTeacherActivity {

    private final StudentAssignmentService assignmentService;
    private final StudentExamService examService;
    private final StudentQuizService quizService;


    @Autowired
    public StudentTeacherActivity(StudentAssignmentService assignmentService, StudentExamService examService, StudentQuizService quizService) {
        this.assignmentService = assignmentService;
        this.examService = examService;
        this.quizService = quizService;
    }

//    public void Disable(List<StudentAssignment> assignments, List<StudentExam> exams, List<StudentQuiz> quizzes,
//                        List<TeacherAssignment> teacherAssignments, List<TeacherExams> teacherExams, List<TeacherQuizzes> teacherQuizzes){
//
//
//
//    }

    public void DisableStudentAssignment(Set<StudentAssignment> assignments){
        for(StudentAssignment assignment: assignments){
            assignment.setStatus(0);
            assignmentService.save(assignment);
        }
    }

    public void DisableStudentExam(Set<StudentExam> exams){
        for(StudentExam exam: exams){
            exam.setStatus(0);
            examService.save(exam);
        }
    }

    public void DisableStudentQuiz(Set<StudentQuiz> quizs){
        for(StudentQuiz studentQuiz: quizs){
            studentQuiz.setStatus(0);
            quizService.save(studentQuiz);
        }
    }

    public void EnableStudentAssignment(Set<StudentAssignment> assignments){
        for(StudentAssignment assignment: assignments){
            if(assignment.getLocation()==null||assignment.getLocation().trim().isEmpty())assignment.setStatus(-1);
            else assignment.setStatus(1);
            assignmentService.save(assignment);
        }
    }

    public void EnableStudentExam(Set<StudentExam> exams){
        for(StudentExam exam: exams){
            if(exam.getLocation() == null ||exam.getLocation().trim().isEmpty())exam.setStatus(-1);
            else exam.setStatus(1);
            examService.save(exam);
        }
    }

    public void EnableStudentQuiz(Set<StudentQuiz> quizs){
        for(StudentQuiz studentQuiz: quizs){
            if(studentQuiz.getLocation() == null ||
                    studentQuiz.getLocation().trim().isEmpty()) studentQuiz.setStatus(-1);
            else studentQuiz.setStatus(1);
            quizService.save(studentQuiz);
        }
    }



}
