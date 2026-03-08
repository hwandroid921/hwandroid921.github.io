package com.tour.jeju.service;

import com.tour.jeju.config.FileConfig;
import com.tour.jeju.dto.MythRequest;
import com.tour.jeju.entity.Myth;
import com.tour.jeju.repository.MythRepository;
import com.tour.jeju.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MythService {

    private final MythRepository mythRepository;
    private final FileConfig fileConfig;

    //신규등록
    public Long insert(MythRequest dto) throws Exception {
        String filename = FileUploadUtil.saveFile(fileConfig.uploadDir, dto.getUrlPath1());

        Myth myth = Myth.builder()
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

        return mythRepository.save(myth).getId();
    }

    //수정
    public void edit(Long id, MythRequest dto) throws Exception {
        Myth myth = mythRepository.findById(id).orElseThrow();

        if(dto.getUrlPath1() != null && !dto.getUrlPath1().isEmpty()) {
            String fileName = FileUploadUtil.saveFile(fileConfig.uploadDir, dto.getUrlPath1());
            myth.setUrlPath1(fileName);
        }

        myth.setName(dto.getName());
        myth.setDescription(dto.getDescription());
        myth.setUrlPath2(dto.getUrlPath2());
        myth.setUrlPath3(dto.getUrlPath3());
        myth.setUrlPath4(dto.getUrlPath4());
        myth.setUrlPath5(dto.getUrlPath5());
        myth.setUrlPath6(dto.getUrlPath6());
        myth.setUrlPath7(dto.getUrlPath7());
        myth.setUrlPath8(dto.getUrlPath8());
    }

    //목록
    public List<Myth> findAll() {
        return mythRepository.findAll();
    }

    //삭제
    public void delete(Long id) {
        mythRepository.deleteById(id);
    }

    //상세 정보
    public Myth findById(Long id) {
        return mythRepository.findById(id).orElseThrow();
    }
}
