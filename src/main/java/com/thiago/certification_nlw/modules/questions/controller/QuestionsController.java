package com.thiago.certification_nlw.modules.questions.controller;

import org.springframework.web.bind.annotation.RestController;

import com.thiago.certification_nlw.modules.questions.dto.AlternativesResultDTO;
import com.thiago.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.thiago.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.thiago.certification_nlw.modules.questions.entities.QuestionEntity;
import com.thiago.certification_nlw.modules.questions.repositories.QuestionRepository;

import java.util.List;
//import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/questions")

public class QuestionsController {
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        System.out.println("TECH===" + technology);
        var result = this.questionRepository.findByTechnology(technology);
        var toMap = result.stream().map(question -> mapQuestionToDTO(question))
                .collect(Collectors.toList());
        return toMap;
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder()
                .id(question.getId())
                .technology(question.getTechnology())
                .description(question.getDescription()).build();

        List<AlternativesResultDTO> alternativesResultDTOs = question.getAlternatives()
                .stream().map(alternative -> mapAlternativeDto(alternative))
                .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOs);
        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativeDto(AlternativesEntity alternativesResultDTO) {
        return AlternativesResultDTO.builder()
                .id(alternativesResultDTO.getId())
                .description(alternativesResultDTO.getDescription()).build();
    }

}
