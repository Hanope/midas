package com.midas.cafe.api.admin;

import com.midas.cafe.model.CouponDTO;
import com.midas.cafe.model.Result;
import com.midas.cafe.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/coupon")
public class CouponAPI {

  @Autowired
  private CouponService couponService;

  @PostMapping()
  public Result addCoupon(@RequestBody CouponDTO coupon) {
    return couponService.addCoupon(coupon);
  }
}
