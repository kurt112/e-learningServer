package com.thesis.ELearning.repository;

import com.thesis.ELearning.entity.StudentLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentLogsRepository extends JpaRepository<StudentLogs, Integer> {
}
