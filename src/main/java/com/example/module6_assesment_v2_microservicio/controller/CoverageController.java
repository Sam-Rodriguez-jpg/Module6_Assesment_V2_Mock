package com.example.module6_assesment_v2_microservicio.controller;

import com.example.module6_assesment_v2_microservicio.dto.CoverageRequest;
import com.example.module6_assesment_v2_microservicio.dto.InsuranceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/coverage-evaluation")
public class CoverageController {

    @PostMapping
    public ResponseEntity<InsuranceResponse> evaluate(@RequestBody CoverageRequest request) {
        // Generamos el seed usando el hash del documento del paciente
        long seed = (request.document() != null) ? request.document().hashCode() : 0;
        Random random = new Random(seed);

        // Porcentaje entre 50 y 100
        int percentage = 50 + random.nextInt(51);

        // Clasificación de niveles según el enunciado
        String level;
        if (percentage >= 90) level = "ALTA";
        else if (percentage >= 70) level = "MEDIA";
        else level = "BAJA";

        InsuranceResponse response = new InsuranceResponse(
                request.document(),
                percentage,
                level,
                random.nextBoolean(),
                "Automatic evaluation performed for service code: " + request.serviceCode()
        );

        return ResponseEntity.ok(response);
    }
}
