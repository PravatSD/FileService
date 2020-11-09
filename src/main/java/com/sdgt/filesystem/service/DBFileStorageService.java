package com.sdgt.filesystem.service;

import com.sdgt.filesystem.exception.FileStorageException;
import com.sdgt.filesystem.exception.MyFileNotFoundException;
import com.sdgt.filesystem.model.DBFile;
import com.sdgt.filesystem.property.FileStorageProperties;
import com.sdgt.filesystem.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    private final Path fileStorageLocation;

    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Autowired
    public DBFileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
               .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }



    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }



            /**
             *
             */

            String orgCode="HUMS";
            String unitCode="PU";
            String docCode="Patient";
            String docType="PDF";
            String fContentType=file.getContentType();
           // String newCo=fContentType.substring()
          //  String s=newCo.trim().toUpperCase();

            Path targetPath= Paths.get("D:/PROJECTS/PRODUCT/HUMS/PU/Patient/PDF").toAbsolutePath().normalize();

            String basePath=fileStorageProperties.getBaseDir();
            String wholePath=basePath+"/"+orgCode+"/"+unitCode+"/"+docCode+"/"+docType;

            Path newPath=Paths.get(wholePath).toAbsolutePath().normalize();
            String targetPath1= "D:/PROJECTS/PRODUCT/HUMS/PU/Patient/PDF";
            String targetPath2= "D:\\PROJECTS\\PRODUCT\\HUMS\\PU\\Patient\\PDF";
            String filepath=targetPath.toString();

            File file1=new File(targetPath2,fileName);
            System.out.println(file1);
            ////
           // Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Path targetLocation = targetPath.resolve(fileName);
            Path targetLocation1 = newPath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation1, StandardCopyOption.REPLACE_EXISTING);



            //  Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes(),filepath);
            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
