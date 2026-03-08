package com.tour.jeju.service;

import com.tour.jeju.config.FileConfig;
import com.tour.jeju.dto.CultureRequest;
import com.tour.jeju.entity.Culture;
import com.tour.jeju.repository.CultureRepository;
import com.tour.jeju.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CultureService {

    private final CultureRepository cultureRepository;
    private final FileConfig fileConfig;

    //신규 등록
    public Long insert(CultureRequest dto) throws Exception {
        String filename = FileUploadUtil.saveFile(fileConfig.uploadDir, dto.getUrlPath1());

        Culture culture = Culture.builder()
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

        return cultureRepository.save(culture).getId();
    }

    //수정
    public void edit(Long id, CultureRequest dto) throws Exception {
        Culture Culture = cultureRepository.findById(id).orElseThrow();

        if(dto.getUrlPath1() != null && !dto.getUrlPath1().isEmpty()) {
            String fileName = FileUploadUtil.saveFile(fileConfig.uploadDir, dto.getUrlPath1());
            Culture.setUrlPath1(fileName);
        }

        Culture.setName(dto.getName());
        Culture.setDescription(dto.getDescription());
        Culture.setUrlPath2(dto.getUrlPath2());
        Culture.setUrlPath3(dto.getUrlPath3());
        Culture.setUrlPath4(dto.getUrlPath4());
        Culture.setUrlPath5(dto.getUrlPath5());
        Culture.setUrlPath6(dto.getUrlPath6());
        Culture.setUrlPath7(dto.getUrlPath7());
        Culture.setUrlPath8(dto.getUrlPath8());
    }

    //목록
    public List<Culture> findAll() {
        return cultureRepository.findAll();
    }

    //삭제
    public void delete(Long id) {
        cultureRepository.deleteById(id);
    }

    public Culture findById(Long id) {
        return cultureRepository.findById(id).orElseThrow();
    }
}
