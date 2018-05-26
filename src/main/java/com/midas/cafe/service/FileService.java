package com.midas.cafe.service;

import com.midas.cafe.model.FileVO;
import com.midas.cafe.repository.FileDao;
import java.io.File;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  @Autowired
  private FileDao fileDao;

  public String addFile(MultipartFile file) throws Exception {
    FileVO fileVO = new FileVO();

    if (file.isEmpty())
      return null;

    String fileName = file.getOriginalFilename();
    String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
    File destinationFile;
    String destinationFileName;
    String fileUrl = "/Users/heemanghan/Desktop/midas/cafe/src/main/resources/static/img/";

    do {
      destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
      destinationFile = new File(fileUrl + destinationFileName);
    } while (destinationFile.exists());

    destinationFile.getParentFile().mkdirs();
    file.transferTo(destinationFile);
  }
}
