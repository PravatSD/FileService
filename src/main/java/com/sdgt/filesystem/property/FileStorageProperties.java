package com.sdgt.filesystem.property;



import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {


 private String baseDir;
 private String uploadDir;

 public String getUploadDir() {
  return uploadDir;
 }

 public void setUploadDir(String uploadDir) {
  this.uploadDir = uploadDir;
 }

 public String getBaseDir() {
  return baseDir;
 }

 public FileStorageProperties setBaseDir(String baseDir) {
  this.baseDir = baseDir;
  return this;
 }
}
