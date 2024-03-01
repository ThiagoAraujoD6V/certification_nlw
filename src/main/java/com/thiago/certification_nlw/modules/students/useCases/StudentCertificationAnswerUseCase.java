package com.thiago.certification_nlw.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.certification_nlw.modules.questions.entities.QuestionEntity;
import com.thiago.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.thiago.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.thiago.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.thiago.certification_nlw.modules.students.entities.AnswersCertificationsEntity;
import com.thiago.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.thiago.certification_nlw.modules.students.entities.StudentEntity;
import com.thiago.certification_nlw.modules.students.repositores.CertificationStudentRepository;
import com.thiago.certification_nlw.modules.students.repositores.StudentRepository;

@Service
public class StudentCertificationAnswerUseCase {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CertificationStudentRepository certificationStudentRepository;
    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {
        var hasCertification = this.verifyIfHasCertificationUseCase
                .execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));
        if (hasCertification) {
            throw new Exception("Voce ja tirou a sua certificação!");
        }
        // verificar se o usuario ele existe
        // Buscar as alternativas das perguntas
        // -correta ou incorreta
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();
        AtomicInteger correctAnswers = new AtomicInteger(0);
        dto.getQuestionsAnswers()
                .stream().forEach(questionsAnswers -> {
                    var question = questionsEntity.stream()
                            .filter(q -> q.getId().equals(questionsAnswers.getQuestionID()))
                            .findFirst().get();

                    var findCorrectAlternative = question.getAlternatives().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if (findCorrectAlternative.getId().equals(questionsAnswers.getAlternativeID())) {
                        questionsAnswers.setCorrect(true);
                        correctAnswers.incrementAndGet();
                    } else {
                        questionsAnswers.setCorrect(false);
                    }
                    var answersCertificationsEntity = AnswersCertificationsEntity.builder()
                            .answersID(questionsAnswers.getAlternativeID())
                            .questionID(questionsAnswers.getQuestionID())
                            .isCorrect(questionsAnswers.isCorrect()).build();
                    answersCertifications.add(answersCertificationsEntity);
                });

        // verificar se o estudante existe
        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if (student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .technology(dto.getTechnology())
                .studentID(studentID)
                .grade(correctAnswers.get())
                .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);
        answersCertifications.stream().forEach(answersCertification -> {
            answersCertification.setCertificationID(certificationStudentEntity.getId());
            answersCertification.setCertificationStudentEntity(certificationStudentEntity);
        });
        certificationStudentEntity.setAnswersCertificationsEntities(answersCertifications);
        certificationStudentRepository.save(certificationStudentEntity);
        return certificationStudentCreated;
        // salvar as informaçoes da certificação
    }
}
