package com.thesis.ELearning.controller.AdminRest;

import com.thesis.ELearning.entity.API.PagesContent;
import com.thesis.ELearning.service.serviceImplementation.AutoCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/autocomplete")
public class AutoComplete {

    final private AutoCompleteService autoCompleteService;


    @Autowired
    public AutoComplete(AutoCompleteService autoCompleteService) {
        this.autoCompleteService = autoCompleteService;
    }


    @GetMapping("/teacher")
    public PagesContent<Object> TeacherAutoComplete(@RequestParam("search") String search){

        Page<Object> current = autoCompleteService.Teacher(search);
        return new PagesContent<>(current.getContent(),current.getTotalElements(),current.getTotalPages(),current.getNumber());
    }

    @GetMapping("/room")
    public PagesContent<Object> RoomAutoComplete(@RequestParam("search") String search) {
        Page<Object> current = autoCompleteService.Room(search);
        return new PagesContent<>(current.getContent(),current.getTotalElements(),current.getTotalPages(),current.getNumber());
    }

    @GetMapping("/subject")
    public PagesContent<Object> SubjectAutoComplete(@RequestParam("search") String search) {
        Page<Object> current = autoCompleteService.Subject(search);
        return new PagesContent<>(current.getContent(),current.getTotalElements(),current.getTotalPages(),current.getNumber());
    }

    @GetMapping("/roomShift")
    public PagesContent<Object> RoomShiftAutoComplete(@RequestParam("search") String search) {
        Page<Object> current = autoCompleteService.RoomShift(search);
        return new PagesContent<>(current.getContent(),current.getTotalElements(),current.getTotalPages(),current.getNumber());
    }

    @GetMapping("/classes")
    public PagesContent<Object> RoomClassesAutoComplete(@RequestParam("search") String search){
        Page<Object> current = autoCompleteService.RoomClass(search);
        return new PagesContent<>(current.getContent(),current.getTotalElements(),current.getTotalPages(),current.getNumber());
    }
    
    /**
     *
     *
     *           This auto complete api is for Adding Activity
     *
     */


    @GetMapping("/subjectBaseOnRoomShift")
    public PagesContent<Object> RoomClassDependsOnSubjectAutoComplete(@RequestParam("roomShiftID") int RoomShiftID){
        Page<Object> current = autoCompleteService.SubjectsBasedOnRoomShift(RoomShiftID);
        return new PagesContent<>(current.getContent(),current.getTotalElements(),current.getTotalPages(),current.getNumber());
    }


}
