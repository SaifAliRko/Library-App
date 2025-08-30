package com.libraryapp.spring_boot_library.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import com.libraryapp.spring_boot_library.entity.History;

@RepositoryRestResource(collectionResourceRel = "histories", path = "histories")
public interface HistoryRepository extends JpaRepository<History, Long> {
  Page<History> findBooksByUserEmail(@RequestParam("userEmail") String userEmail, Pageable pageable);
}