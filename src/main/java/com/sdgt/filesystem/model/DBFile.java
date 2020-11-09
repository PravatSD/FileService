package com.sdgt.filesystem.model;

import javax.persistence.*;

@Entity
@Table(name = "files1")
public class DBFile  extends BaseFileEntity{
   /* @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;*/

    private String fileName;

    private String fileFormat;

    private String fileLocation;

    private String fileType;

    private String mrn;
    private String patientId;

    private String visitNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DocDetails docDetails;







    @Lob
    private byte[] data;

    public DBFile() {

    }

    public DBFile(String fileName, String fileFormat, byte[] data,String fileLocation) {
        this.fileName = fileName;
        this.fileFormat = fileFormat;
        this.data = data;
        this.fileLocation=fileLocation;
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    public String getFileLocation() {
        return fileLocation;
    }

    public DBFile setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public DBFile setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public String getMrn() {
        return mrn;
    }

    public DBFile setMrn(String mrn) {
        this.mrn = mrn;
        return this;
    }

    public String getPatientId() {
        return patientId;
    }

    public DBFile setPatientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    public String getVisitNo() {
        return visitNo;
    }

    public DBFile setVisitNo(String visitNo) {
        this.visitNo = visitNo;
        return this;
    }

    public DocDetails getDocDetails() {
        return docDetails;
    }

    public DBFile setDocDetails(DocDetails docDetails) {
        this.docDetails = docDetails;
        return this;
    }
}
