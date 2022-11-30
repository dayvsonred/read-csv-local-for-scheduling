package com.example.demo.dto

data class Agendamento (
    val id: String,
    val data: String,
    val horaInicio: String,
    val horaFim: String,
    val status: String,
    val rua: String,
    val numero: String,
    val bairro: String,
    val complemento: String,
    val cidade: String,
    val estado: String,
    val cep: String,
    val nome: String,
    val tipo: String,
    val telefone: String,
    val exames: String,
)