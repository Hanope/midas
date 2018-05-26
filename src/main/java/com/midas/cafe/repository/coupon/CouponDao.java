package com.midas.cafe.repository.coupon;

import com.midas.cafe.model.CouponDTO;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public int insertCoupon(CouponDTO coupon) {
    String query = "INSERT INTO mi_coupon(code, name, start_date, end_date,"
        + " category_code, discount_per) VALUES(?, ?, ?, ?, ?, ?)";
    return jdbcTemplate.update(query, coupon.getCode(), coupon.getName(), coupon.getStartDate(), coupon.getEndDate(), coupon.getCategoryCode(), coupon.getDiscountPercent());
  }

  public int insertUserCoupon(CouponDTO coupon) {
    String query = "INSERT INTO mi_user_coupon(loginid, coupon_code, issued_dt, use_dt) VALUES(?, ?, ?, ?)";
    return jdbcTemplate.update(query, coupon.getUserId(), coupon.getCode(), new Date(), null);
  }


}
