package com.tour.jeju.repository;

import com.tour.jeju.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 마이페이지 -> 관리자는 회원 정보 찾기
    Optional<Member> findByUsername(String username);

    // 아이디 중복 확인
    boolean existsByUsername(String username);

    // 관리자가 이름으로 회원 정보 검색
    Page<Member> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    // 관리자가 아이디로 회원 정보 검색
    Page<Member> findByUsernameContainingIgnoreCase(String keyword, Pageable pageable);

    // 관리자가 이름 또는 아이디로 회원 정보 검색
    Page<Member> findByNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(String username, String name, Pageable pageable);

    // 검색 창에 입력한 값이 아이디이든 이름이든 상관없이 둘 다 뒤져서 결과 반환
    @Query("SELECT m FROM Member m WHERE " +
            "LOWER(m.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Member> searchByUsernameOrName(@Param("keyword") String keyword, Pageable pageable);
}

