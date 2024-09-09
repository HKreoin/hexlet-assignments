package exercise.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {
    
    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "\\+\\d{11,13}", message = "Phone number must start with + and contain between 11 and 13 digits")
    private String phoneNumber;

    @Pattern(regexp = "\\d{4}", message = "Club card number must consist of exactly 4 digits")
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;
}
// END
