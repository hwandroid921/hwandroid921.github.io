package com.tour.jeju.service;

import com.tour.jeju.config.FileConfig;
import com.tour.jeju.dto.FestivalRequest;
import com.tour.jeju.entity.Festival;
import com.tour.jeju.repository.FestivalRepository;
import com.tour.jeju.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final FileConfig fileConfig;

    //신규등록
    public Long insert(FestivalRequest dto) throws Exception {
        String filename = FileUploadUtil.saveFile(fileConfig.uploadDir, dto.getUrlPath1());

        Festival festival = Festival.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .urlPath1(filename)
                .urlPath2(dto.getUrlPath2())
                .urlPath3(dto.getUrlPath3())
                .urlPath4(dto.getUrlPath4())
                .urlPath5(dto.getUrlPath5())
                .urlPath6(dto.getUrlPath6())
                .urlPath7(dto.getUrlPath7())
                .urlPath8(dto.getUrlPath8())
                .build();

        return festivalRepository.save(festival).getId();
    }

    //수정
    public void edit(Long id, FestivalRequest dto) throws Exception {
        Festival festival = festivalRepository.findById(id).orElseThrow();

        if(dto.getUrlPath1() != null && !dto.getUrlPath1().isEmpty()) {
            String fileName = FileUploadUtil.saveFile(fileConfig.uploadDir, dto.getUrlPath1());
            festival.setUrlPath1(fileName);
        }

        festival.setName(dto.getName());
        festival.setDescription(dto.getDescription());
        festival.setUrlPath2(dto.getUrlPath2());
        festival.setUrlPath3(dto.getUrlPath3());
        festival.setUrlPath4(dto.getUrlPath4());
        festival.setUrlPath5(dto.getUrlPath5());
        festival.setUrlPath6(dto.getUrlPath6());
        festival.setUrlPath7(dto.getUrlPath7());
        festival.setUrlPath8(dto.getUrlPath8());
    }

    //목록
    public List<Festival> findAll() {
        return festivalRepository.findAll();
    }

    //삭제
    public void delete(Long id) {
        festivalRepository.deleteById(id);
    }

    //상세 정보
    public Festival findById(Long id) {
        return festivalRepository.findById(id).orElseThrow();
    }
}
