package FEB2022.core2;

import FEB2022.core2.discount.DiscountPolicy;
import FEB2022.core2.discount.FixDiscountPolicy;
import FEB2022.core2.discount.RateDiscountPolicy;
import FEB2022.core2.member.MemberRepository;
import FEB2022.core2.member.MemberService;
import FEB2022.core2.member.MemberServiceImpl;
import FEB2022.core2.member.MemoryMemberRepository;
import FEB2022.core2.order.OrderService;
import FEB2022.core2.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
