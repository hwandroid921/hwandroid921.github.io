package com.tour.jeju.service;

import com.tour.jeju.config.FileConfig;
import com.tour.jeju.dto.UnescoRequest;
import com.tour.jeju.entity.Unesco;
import com.tour.jeju.repository.UnescoRepository;
import com.tour.jeju.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UnescoService {

    private final UnescoRepository unescoRepository;
    private final FileConfig fileConfig;

    //신규등록
    public Long insert(UnescoRequest dto) throws Exception {
        String filename = FileUploadUtil.saveFile(fileConfig.uploadDir, dto.getUrlPath1());

        Unesco unesco = Unesco.builder()
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

        return unescoRepository.save(unesco).getId();
    }

    //수정
    public void edit(Long id, UnescoRequest dto) throws Exception {
        Unesco unesco = unescoRepository.findById(id).orElseThrow();

        if(dto.getUrlPath1() != null && !dto.getUrlPath1().isEmpty()) {
            String fileName = FileUploadUtil.saveFile(fileConfig.uploadDir, dto.getUrlPath1());
            unesco.setUrlPath1(fileName);
        }

        unesco.setName(dto.getName());
        unesco.setDescription(dto.getDescription());
        unesco.setUrlPath2(dto.getUrlPath2());
        unesco.setUrlPath3(dto.getUrlPath3());
        unesco.setUrlPath4(dto.getUrlPath4());
        unesco.setUrlPath5(dto.getUrlPath5());
        unesco.setUrlPath6(dto.getUrlPath6());
        unesco.setUrlPath7(dto.getUrlPath7());
        unesco.setUrlPath8(dto.getUrlPath8());
    }

    //목록
    public List<Unesco> findAll() {
        return unescoRepository.findAll();
    }

    //삭제
    public void delete(Long id) {
        unescoRepository.deleteById(id);
    }

    //상세 정보
    public Unesco findById(Long id) {
        return unescoRepository.findById(id).orElseThrow();
    }
}
