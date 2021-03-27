package com.example.pressurepoints;

import DTO.AnswersFlowDTO;

public class AnswersDataFlowControl {

    private static AnswersFlowDTO answersForFlowDTO;

    public AnswersDataFlowControl() {
    }

    public AnswersDataFlowControl(AnswersFlowDTO answersForFlowDTO) {
        this.answersForFlowDTO = answersForFlowDTO;
    }

    public static AnswersFlowDTO getAnswersForFlowDTO() {
        if (answersForFlowDTO == null){
            answersForFlowDTO = new AnswersFlowDTO();
        }
        return answersForFlowDTO;
    }

    private static void setAnswersForFlowDTO(AnswersFlowDTO answersForFlowDTO) {
        AnswersDataFlowControl.answersForFlowDTO = answersForFlowDTO;
    }
}
