package dat3.cars_r_us.dto;

import dat3.cars_r_us.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;

    public static Member getMemberEntity(MemberRequest memberRequest){
        return new Member(
                memberRequest.username,
                memberRequest.getPassword(),
                memberRequest.getEmail(),
                memberRequest.firstName,
                memberRequest.lastName,
                memberRequest.getStreet(),
                memberRequest.getCity(),
                memberRequest.getZip()
        );
    }

    // Member to MemberRequest conversion
    public MemberRequest(Member member){
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.email = member.getEmail();
        this.street = member.getStreet();
        this.city = member.getCity();
        this.zip = member.getZip();
    }
}


