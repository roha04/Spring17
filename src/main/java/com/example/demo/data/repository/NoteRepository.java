package com.example.demo.data.repository;

import com.example.demo.data.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Integer>{
    @Query(nativeQuery = true, value = "SELECT COALESCE(max(id),0) FROM note")
    int LastNoteId();
    @Query("FROM NoteEntity n WHERE n.title = :title")
    Optional<NoteEntity> findByTitle (@Param("title") String title);
    Optional<NoteEntity> findTopByOrderByIdDesc();
    /** що до пояснення від https://www.javaguides.net/2023/08/spring-data-jpa-find-max-value.html#google_vignette
     * findTopBy обмежить результат лише одним записом OrderByIdDesc упорядкує нотатки за їх ідентифікатором
     * в порядку спадання, тому перша нотатка матиме найвищий Id
     */

}
