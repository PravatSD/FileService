package com.sdgt.filesystem.service;

import com.sdgt.filesystem.model.DocDetails;
import com.sdgt.filesystem.repository.DcoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocService {


 @Autowired
 DcoRepository dcoRepository;

 public List<DocDetails> getDocListData() {
  List<DocDetails> docDetails = dcoRepository.GetDocList();
  return getList1(docDetails);
 }

 public List<DocDetails> getDocListDataById(Long parentId) {
  List<DocDetails> docDetails = dcoRepository.GetDocList();
  return getListByParentId(docDetails,parentId);
 }

 /**
  *
  */
 public List<DocDetails> getList(List<DocDetails> list) {

 // List<DocDetails> firstList = list.stream().filter((docDetails) -> docDetails.getParentDocId().compareTo(0L)).collect(Collectors.toList());
  List<DocDetails> firstList = list.stream().filter(docDetails -> docDetails.getParentDocId().toString().equals("0")).collect(Collectors.toList());
  for (DocDetails secondList : firstList) {
   secondList.setChildren(list.stream().filter((docDetails )-> docDetails.getParentDocCode().equals(secondList.getParentDocCode())).collect(Collectors.toList()));
   for (DocDetails thirdList : secondList.getChildren()) {

    List<DocDetails> fourthList = list.stream().filter((docDetails) -> docDetails.getParentDocCode().equals(thirdList.getParentDocCode())).collect(Collectors.toList());
    if (fourthList.size() > 0) {
     thirdList.setChildren(fourthList);
    }
    for (DocDetails fifthList : thirdList.getChildren()) {
     List<DocDetails> docDetailsList = list.stream().filter((docDetails )-> docDetails.getParentDocCode().equals(fifthList.getParentDocCode())).collect(Collectors.toList());
     if (docDetailsList.size() > 0) {
      fifthList.setChildren(docDetailsList);
     }

    }

   }
  }


  return firstList;
 }



 public List<DocDetails> getList1(List<DocDetails> list){
  List<DocDetails> zeroList = list.stream().filter((menuMaster) -> menuMaster.getParentDocId().equals("0")).collect(Collectors.toList());
  for(DocDetails obj:zeroList) {
   obj.setChildren(list.stream().filter((menuMaster) -> menuMaster.getParentDocId().equals(obj.getId())).collect(Collectors.toList()));
   for(DocDetails master:obj.getChildren()) {

    List<DocDetails> listObj = list.stream().filter((menuMaster) -> menuMaster.getParentDocId().equals(master.getId())).collect(Collectors.toList());
    if(listObj.size() > 0)
    {
     master.setChildren(listObj);
    }

    for(DocDetails menuMaster: master.getChildren()){
     List<DocDetails> menuMasterList = list.stream().filter((menumaster) -> menumaster.getParentDocId().equals(menuMaster.getId())).collect(Collectors.toList());
     //List<DocDetails> menuMasterList = list.stream().limit(10);
     if(menuMasterList.size() > 0)
     {
      menuMaster.setChildren(menuMasterList);
     }
    }

   }

  }

  return zeroList;
 }

 public List<DocDetails> getListByParentId(List<DocDetails> list,Long parentId){
  List<DocDetails> zeroList = list.stream().filter((menuMaster) -> menuMaster.getId().equals(parentId)).collect(Collectors.toList());
  for(DocDetails obj:zeroList) {
   obj.setChildren(list.stream().filter((menuMaster) -> menuMaster.getParentDocId().equals(obj.getId())).collect(Collectors.toList()));
   for(DocDetails master:obj.getChildren()) {

    List<DocDetails> listObj = list.stream().filter((menuMaster) -> menuMaster.getParentDocId().equals(master.getId())).collect(Collectors.toList());
    if(listObj.size() > 0)
    {
     master.setChildren(listObj);
    }

    for(DocDetails menuMaster: master.getChildren()){
     List<DocDetails> menuMasterList = list.stream().filter((menumaster) -> menumaster.getParentDocId().equals(menuMaster.getId())).collect(Collectors.toList());
     //List<DocDetails> menuMasterList = list.stream().limit(10);
     if(menuMasterList.size() > 0)
     {
      menuMaster.setChildren(menuMasterList);
     }
    }

   }

  }

  return zeroList;
 }

 public List<DocDetails> getDocListDataByMrn(String mrn) {
 List<DocDetails> docDetailsList=dcoRepository.findByMrn(mrn);

  Optional<DocDetails> highestPaidEmployeeWrapper=
         docDetailsList.stream().collect(Collectors.minBy(Comparator.comparingLong(DocDetails::getParentDocId)));
 //Long maxId=docDetailsList.stream().max(docDetailsList1-> Math.toIntExact(docDetailsList1.getParentDocId()));

  DocDetails highestPaidEmployee = highestPaidEmployeeWrapper.get();
 Long maxId=highestPaidEmployee.getParentDocId();
 return getListByParentId1(docDetailsList,maxId);
 }



 public List<DocDetails> getListByParentId1(List<DocDetails> list,Long parentId){
  List<DocDetails> zeroList = list.stream().filter((menuMaster) -> menuMaster.getParentDocId().equals(parentId)).distinct().collect(Collectors.toList());
  for(DocDetails obj:zeroList) {
   obj.setChildren(list.stream().filter((menuMaster) -> menuMaster.getParentDocId().equals(obj.getParentDocId())).collect(Collectors.toList()));
   for(DocDetails master:obj.getChildren()) {

    List<DocDetails> listObj = list.stream().filter((menuMaster) -> menuMaster.getParentDocId().equals(master.getParentDocId())).collect(Collectors.toList());
    if(listObj.size() > 0)
    {
     master.setChildren(listObj);
    }

    for(DocDetails menuMaster: master.getChildren()){
     List<DocDetails> menuMasterList = list.stream().filter((menumaster) -> menumaster.getParentDocId().equals(menuMaster.getParentDocId())).collect(Collectors.toList());
     //List<DocDetails> menuMasterList = list.stream().limit(10);
     if(menuMasterList.size() > 0)
     {
      menuMaster.setChildren(menuMasterList);
     }
    }

   }

  }

  return zeroList;
 }
}
