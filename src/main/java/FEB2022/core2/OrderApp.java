package FEB2022.core2;

import FEB2022.core2.member.Grade;
import FEB2022.core2.member.Member;
import FEB2022.core2.member.MemberService;
import FEB2022.core2.order.Order;
import FEB2022.core2.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "itemA", 10000);

        System.out.println("order = " + order);
    }
}
