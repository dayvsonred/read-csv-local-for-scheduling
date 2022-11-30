package com.example.demo.dto

data class Patient(
        val progressId: String,
        val cip: String?,
        val name: String,
        val type: String,
        val genre: String,
        val cpf: String?,
        val height: String?,
        val weight: String?,
        val birthDate: String?,
        val insuranceId: String,
        val healthPlanCode: String,
        val healthCardNumber: String,
        val contacts: List<Contact>,
        val exams: Personal,

)
