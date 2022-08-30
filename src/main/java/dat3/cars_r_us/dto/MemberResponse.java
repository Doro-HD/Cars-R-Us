package dat3.cars_r_us.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars_r_us.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    private String username; //Remember this is the primary key
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime edited;
    private Integer ranking;

    public MemberResponse(Member member) {
        this(member, false);
    }

    //Convert Member Entity to Member DTO
    public MemberResponse(Member member, boolean includeAll) {
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.street = member.getStreet();
        this.firstName =member.getFirstName();
        this.lastName = member.getLastName();
        this.city = member.getCity();
        this.zip = member.getZip();

        if(includeAll) {
            this.created = member.getCreated();
            this.edited = member.getEdited();
            this.ranking = member.getRanking();
        }
    }
}


