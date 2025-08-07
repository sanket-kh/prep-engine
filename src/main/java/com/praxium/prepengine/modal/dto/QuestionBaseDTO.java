package com.praxium.prepengine.modal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionBaseDTO {
    private String questionText;

    private String questionType; // Consider using an Enum here

    private String rationale;

    private Byte difficultyLevel = 1;

}
