package com.example.baitap.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5, max = 100, message = "Tên phải có từ 5 đến 100 ký tự")
    @Pattern(regexp = "^[a-zA-ZÀ-ỹ\\s]+$", message = "Tên không được chứa ký tự đặc biệt")
    private String name;

    @NotNull(message = "Ngày sinh không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotNull(message = "Kinh nghiệm không được để trống")
    @Positive(message = "Kinh nghiệm phải là số nguyên dương")
    private Integer experience;

    @NotBlank(message = "Vị trí không được để trống")
    @Pattern(
            regexp = "^(trung vệ|hậu vệ|tiền vệ|tiền đạo|thủ môn)$",
            message = "Vị trí phải là 1 trong các giá trị: trung vệ, hậu vệ, tiền vệ, tiền đạo, thủ môn"
    )
    private String position;

    private String avatar;


    public boolean isValidAge() {
        if (dob == null) return false;
        int age = Period.between(dob, LocalDate.now()).getYears();
        return age >= 16 && age <= 100;
    }
}
