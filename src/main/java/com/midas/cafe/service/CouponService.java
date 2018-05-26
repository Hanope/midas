package com.midas.cafe.service;

import com.midas.cafe.model.CouponDTO;
import com.midas.cafe.model.Result;
import com.midas.cafe.repository.coupon.CouponDao;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

  @Autowired
  private CouponDao couponDao;

  public Result addCoupon(CouponDTO coupon) {
    String couponCode = RandomStringUtils.randomAlphanumeric(20);
    coupon.setCode(couponCode);
    couponDao.insertCoupon(coupon);
    couponDao.insertUserCoupon(coupon);

    return new Result(true, coupon);
  }
}
