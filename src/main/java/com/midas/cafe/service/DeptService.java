package com.midas.cafe.service;

import com.midas.cafe.repository.dept.DeptDao;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptService {

  @Autowired
  private DeptDao deptDao;

  public List<Map<String, Object>> findAllDept() {
    return deptDao.findAllDept();
  }

}
