package com.thesis.ELearning.service.serviceImplementation;

import com.thesis.ELearning.repository.AutoCompleteRepository;
import com.thesis.ELearning.service.PageableService.PageableServiceAutoComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class AutoCompleteService implements PageableServiceAutoComplete<Object> {

    final private AutoCompleteRepository repo;

    @Autowired
    public AutoCompleteService(AutoCompleteRepository repo) {
        this.repo = repo;
    }


    @Override
    public Page<Object> Room(String search) {
        Pageable pageable = PageRequest.of(0, 15);
        return repo.Room_AutoComplete(search.replaceAll("\\s".toLowerCase(),""),pageable);
    }

    @Override
    public Page<Object> Teacher(String search) {
        Pageable pageable = PageRequest.of(0, 15);
        return repo.Teacher_AutoComplete(search.replaceAll("\\s".toLowerCase(),""),pageable);
    }

    @Override
    public Page<Object> Subject(String search) {
        Pageable pageable = PageRequest.of(0, 15);
        System.out.println("the subject " + search);
        return repo.Subject_AutoComplete(search.replaceAll("\\s".toLowerCase(),""),pageable);
    }

    @Override
    public Page<Object> RoomShift(String search) {
        Pageable pageable = PageRequest.of(0, 15);
        return repo.RoomShift_AutoComplete(search.replaceAll("\\s".toLowerCase(),""),pageable);
    }

    @Override
    public Page<Object> RoomClass(String search) {
        Pageable pageable = PageRequest.of(0, 15);
        return repo.RoomClass(search.replaceAll("\\s".toLowerCase(), ""), pageable);
    }

    @Override
    public Page<Object> SubjectsBasedOnRoomShift(int RoomShiftID) {
        Pageable pageable = PageRequest.of(0, 15);
        return repo.SubjectsBasedOnRoomShift(RoomShiftID, pageable);
    }

    @Override
    public Page<Object> getTeacherClass(String search, String email) {
        Pageable pageable = PageRequest.of(0,15);
        return repo.getTeacherClass(search.replaceAll("\\s".toLowerCase(), ""),email, pageable);
    }

    @Override
    public Page<Object> getTeacherAssignment(String search, String email) {
        Pageable pageable = PageRequest.of(0,15);
        return repo.getTeacherResourcesAssignment(search.replaceAll("\\s".toLowerCase(), ""),email, pageable);
    }

    @Override
    public Page<Object> getTeacherLecture(String search, String email) {
        Pageable pageable = PageRequest.of(0,15);

        return repo.getTeacherResourceLecture(search.replaceAll("\\s".toLowerCase(), ""),email,pageable);
    }
}
