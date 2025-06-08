package com.example.ITWS_Final.repo;

import com.example.ITWS_Final.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepo extends JpaRepository<Entry, Long> {
    List<Entry> findAllByDeletedAtIsNull();

    Optional<Entry> findByIdAndDeletedAtIsNull(Integer id);

    Boolean existsByIdAndDeletedAtIsNull(Integer id);
}
