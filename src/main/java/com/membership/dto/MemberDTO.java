package com.membership.dto;


import com.membership.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberDTO {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private Long id;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private Long pw;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    private int birthday;

    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    private String address;

    public Member toEntity(){
        return Member.builder()
                .id(this.getId())
                .pw(this.getPw())
                .name(this.getName())
                .birthday(this.getBirthday())
                .email(this.getEmail())
                .address(this.getAddress())
                .build();
    }
}
