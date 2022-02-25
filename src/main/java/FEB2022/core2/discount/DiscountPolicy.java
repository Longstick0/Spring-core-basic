package FEB2022.core2.discount;

import FEB2022.core2.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
