package dat3.cars_r_us.api;

import dat3.cars_r_us.dto.MemberRequest;
import dat3.cars_r_us.dto.MemberResponse;
import dat3.cars_r_us.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //Security admin
    @GetMapping
    private List<MemberResponse> getMembers() {
        return this.memberService.findMembers();
    }

    //Security admin
    @GetMapping(path = "/{username}")
    private MemberResponse getMemberById(@PathVariable String username) throws Exception {
        return this.memberService.findMemberByUsername(username);
    }

    //Security user
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PostMapping // same as above when you are using @RestController
    private MemberResponse addMember(@RequestBody MemberRequest body){
        return this.memberService.addMember(body);
    }

    //Security admin/user ??
    @PutMapping("/{username}")
    private MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String username){
        return this.memberService.editMember(body, username);
    }

    //Security admin
    @PatchMapping("/ranking/{username}/{value}")
    private void setRankingForUser(@PathVariable String username, @PathVariable int value) {}

    // Security admin
    @DeleteMapping("/{username}")
    private void deleteMemberByUsername(@PathVariable String username) {}



}
