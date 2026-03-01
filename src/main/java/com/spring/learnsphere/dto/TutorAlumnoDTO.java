package com.spring.learnsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TutorAlumnoDTO {
    private Integer tutorId;
    private Integer alumnoId;
    private String parentesco;
}
