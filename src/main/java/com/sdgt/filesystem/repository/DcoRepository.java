package com.sdgt.filesystem.repository;

import com.sdgt.filesystem.model.DocDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DcoRepository extends JpaRepository<DocDetails,Long> {

 List<DocDetails> GetDocList();

 List<DocDetails> findByMrn(String mrn);
}
