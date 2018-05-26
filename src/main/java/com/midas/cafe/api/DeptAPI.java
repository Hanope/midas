package com.midas.cafe.api;

import com.midas.cafe.model.Result;
import com.midas.cafe.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dept")
public class DeptAPI {

  @Autowired
  private DeptService deptService;

  @GetMapping()
  public Result findAllDept() {
    return new Result(true, deptService.findAllDept());
  }
}
