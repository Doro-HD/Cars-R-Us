package dat3.cars_r_us.service;

import dat3.cars_r_us.dto.MemberRequest;
import dat3.cars_r_us.dto.MemberResponse;
import dat3.cars_r_us.entity.Member;
import dat3.cars_r_us.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> findMembers() {
        List<Member> members = this.memberRepository.findAll();

        return members.stream()
                .map(member -> new MemberResponse(member)).collect(Collectors.toList());
    }

    public MemberResponse addMember(MemberRequest memberRequest){
        //Later you should add error checks --> Missing arguments, email taken etc.

        Member newMember = MemberRequest.getMemberEntity(memberRequest);

        if (memberRepository.existsById(newMember.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this username already exists");
        } else if (memberRepository.existsByEmail(newMember.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this email already exists");
        }

        newMember = memberRepository.save(newMember);

        return new MemberResponse(newMember, false);
    }

    public MemberResponse editMember(MemberRequest memberRequest, String username) {
        Optional<Member> memberOptional = this.memberRepository.findById(username);
        Member member = memberOptional.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        if (username.equals(memberRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change username");
        }

        member.setPassword(memberRequest.getPassword());
        member.setEmail(memberRequest.getEmail());
        member.setFirstName(memberRequest.getFirstName());
        member.setLastName(memberRequest.getLastName());
        member.setStreet(memberRequest.getStreet());
        member.setCity(memberRequest.getCity());
        member.setZip(memberRequest.getZip());

        this.memberRepository.save(member);

        return new MemberResponse(member);
    }

    public MemberResponse findMemberByUsername(String username) throws Exception {
        Member found = memberRepository.findById(username).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

        return new MemberResponse(found,false);
    }



}
