package com.midas.cafe.service;

import com.midas.cafe.model.MenuCategory;
import com.midas.cafe.model.Result;
import com.midas.cafe.repository.admin.AdminDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Autowired
  private AdminDao adminDao;


}
