package com.midas.cafe.service;

import com.midas.cafe.model.CafeMenu;
import com.midas.cafe.repository.menu.MenuDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MenuService {

  @Autowired
  private MenuDao menuDao;

  @Autowired
  private FileService fileService;

  public List<Map<String, Object>> findAllMenu() {
    List<Map<String, Object>> menus = menuDao.findAllMenus();
    return menus;
  }

  public void addMenu(CafeMenu menu, MultipartFile file) {
    menuDao.addMenu(menu, addFile(file));
  }

  public List<Map<String, Object>> findAllByMenuName(String name) {
    List<Map<String, Object>> menus = menuDao.findAllMenusByMenuName(name);
    return menus;
  }

  public CafeMenu updateMenu(CafeMenu menu, MultipartFile file) {
    int fileIdx = fileService.addFile(file);

    if (!file.isEmpty())
      menu.setImgNo(fileIdx);
    menuDao.updateMenu(menu);

    return menu;
  }

  public void deleteMenuByNo(int no) {
    menuDao.deleteByNo(no);
  }

  private int addFile(MultipartFile file) {
    return fileService.addFile(file);
  }
}
