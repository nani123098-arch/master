package com.naniguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Schema(
        title = "DTO class for the USer",
        description = "Some desc"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    @Schema(
            title = "First Name for the user "

    )
    @NotBlank(message = "First name should not be blank")
    private String firstName;

    @NotBlank(message = "Last name should not be blank")
    private String lastName;

    @NotBlank(message = "Email  should not be blank")
    @Email(message = "Enter valid email address")
    private String email;

}
