package com.thiago.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiago.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.thiago.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;

import com.thiago.certification_nlw.modules.students.useCases.StudentCertificationAnswerUseCase;
import com.thiago.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")

public class StudentController {
    // preciso usar o meu USERCASE
    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;
    @Autowired
    private StudentCertificationAnswerUseCase studentCertificationAnswerUseCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        var result = this.verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO);

        if (result) {
            return "Usuario já fez a prova";
        }
        return "Usuario pode fazer a prova";

    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(
            @RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
        try {
            var result = studentCertificationAnswerUseCase.execute(studentCertificationAnswerDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
