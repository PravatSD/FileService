package com.sdgt.filesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="doc_details1")

@NamedQueries({
       @NamedQuery(name = "DocDetails.GetDocList", query = "SELECT e FROM DocDetails e ")})
@JsonIgnoreProperties({ "parent"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DocDetails extends BaseFileEntity {


 private String docType;
 private String docName;
 private String docDescription;
 private Long parentDocId;
 @Column(name="parent_doc_code")
 private String parentDocCode;

 @OneToMany(fetch = FetchType.LAZY)
 private List<DocDetails> children;



 //
 private String fileName;

 private String fileFormat;

 private String fileLocation;

 private String fileType;

 private String mrn;
 private String patientId;

 private String visitNo;


 public String getFileName() {
  return fileName;
 }

 public DocDetails setFileName(String fileName) {
  this.fileName = fileName;
  return this;
 }

 public String getFileFormat() {
  return fileFormat;
 }

 public DocDetails setFileFormat(String fileFormat) {
  this.fileFormat = fileFormat;
  return this;
 }

 public String getFileLocation() {
  return fileLocation;
 }

 public DocDetails setFileLocation(String fileLocation) {
  this.fileLocation = fileLocation;
  return this;
 }

 public String getFileType() {
  return fileType;
 }

 public DocDetails setFileType(String fileType) {
  this.fileType = fileType;
  return this;
 }

 public String getMrn() {
  return mrn;
 }

 public DocDetails setMrn(String mrn) {
  this.mrn = mrn;
  return this;
 }

 public String getPatientId() {
  return patientId;
 }

 public DocDetails setPatientId(String patientId) {
  this.patientId = patientId;
  return this;
 }

 public String getVisitNo() {
  return visitNo;
 }

 public DocDetails setVisitNo(String visitNo) {
  this.visitNo = visitNo;
  return this;
 }
 ////

 public String getDocType() {
  return docType;
 }

 public DocDetails setDocType(String docType) {
  this.docType = docType;
  return this;
 }

 public String getDocName() {
  return docName;
 }

 public DocDetails setDocName(String docName) {
  this.docName = docName;
  return this;
 }

 public String getDocDescription() {
  return docDescription;
 }

 public DocDetails setDocDescription(String docDescription) {
  this.docDescription = docDescription;
  return this;
 }

 public Long getParentDocId() {
  return parentDocId;
 }

 public DocDetails setParentDocId(Long parentDocId) {
  this.parentDocId = parentDocId;
  return this;
 }

 public List<DocDetails> getChildren() {
  return children;
 }

 public DocDetails setChildren(List<DocDetails> children) {
  this.children = children;
  return this;
 }

 public String getParentDocCode() {
  return parentDocCode;
 }

 public DocDetails setParentDocCode(String parentDocCode) {
  this.parentDocCode = parentDocCode;
  return this;
 }
}

