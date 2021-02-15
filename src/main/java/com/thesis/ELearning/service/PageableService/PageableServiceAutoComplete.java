package com.thesis.ELearning.service.PageableService;

import org.springframework.data.domain.Page;

public interface PageableServiceAutoComplete<T> {
    Page<T> Room(String search);

    Page<T> Teacher(String search);

    Page<T> Subject(String search);

    Page<T> RoomShift(String search);

    Page<T> RoomClass(String search);

    Page<T> SubjectsBasedOnRoomShift(int RoomShiftID);

}
