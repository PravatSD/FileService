package com.sdgt.filesystem.controller;

import com.sdgt.filesystem.model.DocDTO;
import com.sdgt.filesystem.model.DocDetails;
import com.sdgt.filesystem.repository.DcoRepository;
import com.sdgt.filesystem.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doc")
public class DocController {

 @Autowired
 DocService docService;


 /*@GetMapping("/{id}/siblings")
 public ResponseEntity<Set<DocDTO>> getAllSiblings(@PathVariable("id") Long id) {
  return dcoRepository.findById(id).map(findSiblings).map(ResponseEntity::ok)
         .orElse(ResponseEntity.notFound().build());
 }

 private Function<DocDetails, Set<DocDTO>> findSiblings = person -> person.getParent().getChildren().stream()
        .map(p -> DocDTO.builder().id(p.getId()).fullName(p.getFullName()).build()).collect(Collectors.toSet());

 private Function<DocDetails, DocDTO> mapToPersonDTO = p -> DocDTO.builder().id(p.getId()).docName(p.getDocName()).parent(p.getParentDoc).children(p.getChildren()).build();
}*/

  @GetMapping("/list")
  public List<DocDetails> getData(){
  List<DocDetails> docDetailsList=docService.getDocListData();
  return docDetailsList;
  }

 @GetMapping("/list/{id}")
 public List<DocDetails> getDataById(@PathVariable Long id){
  List<DocDetails> docDetailsList=docService.getDocListDataById(id);
  return docDetailsList;
 }

 @PostMapping("/listByMrn")
 public List<DocDetails> getDataByMrn(@RequestBody DocDetails docDetails){
  List<DocDetails> docDetailsList=docService.getDocListDataByMrn(docDetails.getMrn());
  return docDetailsList;
 }
}
