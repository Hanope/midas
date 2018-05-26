package com.midas.cafe.model.enumelem;

public enum ReservationStatus {
    REQUEST("예약요청","0"),
    CHECK("주문접수","1"),
    READY("상품준비완료","2"),
    COMPLETE("판매완료","3"),
    ADMIN_CANCEL("관리자취소", "4"),
    USER_CANCEL("사용자취소","5")
    ;

    private String code;
    private String label;

    ReservationStatus(String label, String code)
    {
        this.label = label;
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public static ReservationStatus getValues(String code)
    {
        for(ReservationStatus s : values())
            if(s.getCode().equals(code))
                return s;
        return null;
    }
}