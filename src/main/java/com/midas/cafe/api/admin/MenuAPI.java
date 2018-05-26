package com.midas.cafe.api.admin;

import com.midas.cafe.model.CafeMenu;
import com.midas.cafe.model.Result;
import com.midas.cafe.service.MenuService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/menus")
public class MenuAPI {

  @Autowired
  private MenuService menuService;

  @GetMapping("/{name}")
  public Result findAllMenuByName(@PathVariable String name) {
    List<Map<String, Object>> result = menuService.findAllByMenuName(name);
    if (result.size() == 0) {
      return new Result(false, "검색된 결과가 없습니다.");
    }

    return new Result(true, result);
  }

  // TODO 수정기능 미완성
  @PutMapping()
  public Result updateMenu(@RequestBody CafeMenu menu, MultipartFile file) {
    CafeMenu modifiedMenu = menuService.updateMenu(menu, file);
    return new Result(true, modifiedMenu);
  }

  @DeleteMapping("/{no}")
  public Result deleteMenu(@PathVariable int no) {
    menuService.deleteMenuByNo(no);
    return new Result(true, "삭제가 완료되었습니다.");
  }

}
