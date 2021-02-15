//package com.thesis.ELearning.service.serviceImplementation;
//
//import com.thesis.ELearning.entity.Teacher;
//import com.thesis.ELearning.repository.TeacherFindRepository;
//import com.thesis.ELearning.service.PagableParentClass.ServicePageable;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.data.domain.Sort;
//import javax.transaction.Transactional;
//
//@Transactional
//@Service
//public class sample implements ServicePageable<Teacher> {
//    final private TeacherFindRepository repo;
//
//
//    @Autowired
//    public sample(TeacherFindRepository repo) {
//        this.repo = repo;
//    }
//
//    @Override
//    public Page<Teacher> findTeacher(Integer pageNo, int pageSize) {
//        Sort sort = Sort.by(Sort.Direction.DESC,"user_lastName");
//        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);
//
//        return repo.findAll(pageable);
//    }
//
//    @Override
//    public Page<Teacher> teachers(String name,int page) {
//        Pageable pageable = PageRequest.of(page,1);
//        return repo.TeacherSearch(name,pageable);
//    }
//}
