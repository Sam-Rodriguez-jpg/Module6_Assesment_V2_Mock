package com.example.module6_assesment_v2_microservicio.dto;

public record InsuranceResponse(
        String document,
        Integer coveragePercentage,
        String coverageLevel,
        Boolean copayRequired,
        String observation
) {}
