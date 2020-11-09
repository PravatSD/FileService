package com.sdgt.filesystem.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass

public  abstract class BaseFileEntity  {
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
 @SequenceGenerator(name="seq_gen", sequenceName = "seq_", allocationSize=1, initialValue = 2000)
 private Long id;

 @Column(name = "code",unique = true,length = 50)
 protected String code;

 @Column
 private boolean active =true;

 @Column(name = "created_by")
 private String createdBy;


 @Column(name = "updated_by")
 protected String updatedBy;

 @Column(name = "created_date", nullable = false, updatable = false)
 @Temporal(TemporalType.TIMESTAMP)
 protected Date createdDate;

 @Column(name = "updated_date")
 @Temporal(TemporalType.TIMESTAMP)
 protected Date updatedDate;


 @PrePersist
 void createdAt() {
  this.createdDate = new Date();
 }

 @PreUpdate
 void updatedAt() {
  this.updatedDate = new Date();
 }

 public Long getId() {
  return id;
 }

 public BaseFileEntity setId(Long id) {
  this.id = id;
  return this;
 }

 public boolean isActive() {
  return active;
 }

 public BaseFileEntity setActive(boolean active) {
  this.active = active;
  return this;
 }

 public String getCreatedBy() {
  return createdBy;
 }

 public BaseFileEntity setCreatedBy(String createdBy) {
  this.createdBy = createdBy;
  return this;
 }

 public String getUpdatedBy() {
  return updatedBy;
 }

 public BaseFileEntity setUpdatedBy(String updatedBy) {
  this.updatedBy = updatedBy;
  return this;
 }

 public Date getCreatedDate() {
  return createdDate;
 }

 public BaseFileEntity setCreatedDate(Date createdDate) {
  this.createdDate = createdDate;
  return this;
 }

 public Date getUpdatedDate() {
  return updatedDate;
 }

 public BaseFileEntity setUpdatedDate(Date updatedDate) {
  this.updatedDate = updatedDate;
  return this;
 }

 public String getCode() {
  return code;
 }

 public BaseFileEntity setCode(String code) {
  this.code = code;
  return this;
 }
}
