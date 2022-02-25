package FEB2022.core2;

import FEB2022.core2.member.Grade;
import FEB2022.core2.member.Member;
import FEB2022.core2.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member.getName() = " + member.getName());
        System.out.println("findMember.getName() = " + findMember.getName());
    }
}
