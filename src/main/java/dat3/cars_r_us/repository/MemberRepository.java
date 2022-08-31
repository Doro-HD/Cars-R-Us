package dat3.cars_r_us.repository;

import dat3.cars_r_us.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    boolean existsByEmail(String email);

    long count();

}
