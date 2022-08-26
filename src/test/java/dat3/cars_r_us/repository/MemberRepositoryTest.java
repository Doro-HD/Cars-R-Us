package dat3.cars_r_us.repository;

import dat3.cars_r_us.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    private static String member1Id;
    private static String member2Id;
    private static String member3Id;
    @BeforeAll
    public static void setupData(@Autowired MemberRepository memberRepository) {
        Member member1 = new Member("user1", "lasse@example.com", "password1", "test1", "testsen1");
        Member member2 = new Member("user2", "troels@example.com", "password2", "test2", "testsen2");
        Member member3 = new Member("user3", "victor@example.com", "password3", "test3", "testsen3");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        member1Id = member1.getUsername();
        member2Id = member2.getUsername();
        member3Id = member3.getUsername();
    }

    @Test
    public void deleteById() {
        this.memberRepository.deleteById(member1Id);
        this.memberRepository.deleteById(member2Id);

        Optional<Member> member1 = this.memberRepository.findById(member1Id);
        Optional<Member> member2 = this.memberRepository.findById(member2Id);
        Optional<Member> member3 = this.memberRepository.findById(member3Id);

        assertFalse(member1.isPresent());
        assertFalse(member2.isPresent());
        assertTrue(member3.isPresent());
    }

}