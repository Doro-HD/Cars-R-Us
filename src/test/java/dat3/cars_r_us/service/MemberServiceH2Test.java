package dat3.cars_r_us.service;

import dat3.cars_r_us.dto.MemberRequest;
import dat3.cars_r_us.dto.MemberResponse;
import dat3.cars_r_us.entity.Member;
import dat3.cars_r_us.repository.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class MemberServiceH2Test {

    private MemberService memberService;

    private static MemberRepository memberRepositoryStatic;

    @BeforeAll
    public static void setupData(@Autowired MemberRepository memberRepository){
        memberRepositoryStatic = memberRepository;
        memberRepositoryStatic.deleteAll();
        List<Member> members = List.of(
                new Member("m1", "pw", "m1@a.dk", "aa", "aaa", "aaaa", "aaaa", "1234"),
                new Member("m2", "pw", "mm@a.dk", "bb", "bbb", "bbbb", "bbbb", "1234")
        );
        memberRepositoryStatic.saveAll(members);
    }

    @BeforeEach
    public void setMemberService(){
        memberService = new MemberService(memberRepositoryStatic);
    }

    @Test
    void getMembers() {
        List<MemberResponse> response = memberService.getMembers();
        assertEquals(2,response.size());
        assertThat(response, containsInAnyOrder(hasProperty("email", is("m1@a.dk")), hasProperty("email", is("mm@a.dk"))));
    }


    @Test
    void addMember() {
        Member m = new Member("m3", "pw", "m3@a.dk", "cc", "ccc", "bbbb", "bbbb", "1234");
        MemberRequest request = new MemberRequest(m);
        memberService.addMember(request);

        assertEquals(3, memberRepositoryStatic.count());
    }
}