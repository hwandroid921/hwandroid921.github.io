package com.tour.jeju.dto;

import com.tour.jeju.entity.Attraction;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttractionResponse {

    private Long id;
    private String name;
    private String category;
    private String address;
    private String tel;
    private String description;
    private String thumb;
    private List<String> imageUrls;
    private String mapUrl;
    private List<String> slideUrls;
    private LocalDateTime createdAt;

    private List<ReviewResponse> responses;

    // Entity => dto
    public static AttractionResponse fromEntity(Attraction a, List<ReviewResponse> responses) {
        return AttractionResponse.builder()
                .id(a.getId())
                .name(a.getName())
                .category(a.getCategory())
                .address(a.getAddress())
                .tel(a.getTel())
                .description(a.getDescription())
                .thumb(a.getThumb())
                .imageUrls(a.getImageUrls())
                .mapUrl(a.getMapUrl())
                .slideUrls(a.getSlideUrls())
                .createdAt(a.getCreatedAt())
                .responses(responses)
                .build();
    }
}
