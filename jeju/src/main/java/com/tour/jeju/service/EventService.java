package com.tour.jeju.service;

import com.tour.jeju.config.FileConfig;
import com.tour.jeju.dto.EventRequest;
import com.tour.jeju.entity.Event;
import com.tour.jeju.repository.EventRepository;
import com.tour.jeju.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final FileConfig fileConfig;

    //신규등록
    public Long insert(EventRequest dto) throws Exception {
        String filename = FileUploadUtil.saveFile(fileConfig.uploadDir, dto.getUrlPath());

        Event event = Event.builder()
                .name(dto.getName())
                .period(dto.getPeriod())
                .linkPage(dto.getLinkPage())
                .urlPath(filename)
                .build();

        return eventRepository.save(event).getId();
    }
    
    //수정
    public void edit(Long id, EventRequest dto) throws Exception {
        Event event = eventRepository.findById(id).orElseThrow();

        if(dto.getUrlPath() != null && !dto.getUrlPath().isEmpty()) {
            String fileName = FileUploadUtil.saveFile(fileConfig.uploadDir, dto.getUrlPath());
            event.setUrlPath(fileName);
        }

        event.setName(dto.getName());
        event.setPeriod(dto.getPeriod());
    }

    //목록
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    //삭제
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }
}
