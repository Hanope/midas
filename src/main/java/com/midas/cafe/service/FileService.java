package com.midas.cafe.service;

import com.midas.cafe.model.FileVO;
import com.midas.cafe.repository.FileDao;
import java.io.File;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  @Autowired
  private FileDao fileDao;

  public int addFile(MultipartFile file) {
    int fileIdx = -1;

    if (file.isEmpty())
      return fileIdx;

    String fileName = file.getOriginalFilename();
    String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
    File destinationFile;
    String destinationFileName;
    ApplicationHome home = new ApplicationHome(this.getClass());
    File dir = home.getDir();
    String realFileUrl = dir.getPath() + "/static/img/";
    try {
      do {
        destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
        destinationFile = new File(realFileUrl + destinationFileName);
      } while (destinationFile.exists());

      FileVO fileVO = new FileVO();
      fileVO.setFilename(fileName);
      fileVO.setUrl("/img/" + destinationFileName);
      destinationFile.getParentFile().mkdirs();
      file.transferTo(destinationFile);
      fileIdx = fileDao.addNewFile(fileVO);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return fileIdx;
  }
}
