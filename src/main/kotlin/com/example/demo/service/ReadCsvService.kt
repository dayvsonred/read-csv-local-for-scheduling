package com.example.demo.service

import com.example.demo.dto.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.json.GsonJsonParser
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Service
class ReadCsvService(val gson: Gson) {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    private val brand: Long = 1L
    private val brandIdDataProvider: Long = 1L
    private val brandName: String = "VITALBRASIL"
    private val locationId: String = "AD"
    private val roomId: String = "01"
    private val roomName: String = "VB Carro 02 - Lorena /Cachoira Pta Und10"
    private val scheduleProgressId: Long = 1L
    private val statusFix: String = "SCHEDULED"
    private val cip: String = "6180718492"
    private val type: String = "PRINCIPAL"
    private val genre: String = "FEMININO"
    private val insuranceId: String = "Vb-76"
    private val listExamsPersonal: List<String> = listOf("DA-CJANUR","DA-ATBUR","DA-TIPOUR")
    private val typeHomeCollect: String = "COLLECT"
    private val roomIdHomeCollect: String = "02"









    fun generateJsonOfCsv(): String{
        log.info("start process generateJsonOfCsv")
        log.info("get file")
        val text = File("C:\\Users\\niore\\Documents\\java_projetos\\read csv\\read-csv-local-for-scheduling\\Roteirizador-test.csv").readText()

        log.info("process type file")
        val inputStream = ByteArrayInputStream(text.toByteArray())
        val agendamentos  = readCsv(inputStream)

        log.info("init for")
        var progressId = 100L

        var listAgendamentos: MutableList<ScheduleEvent> = mutableListOf()

        agendamentos.map {
            log.info("------------------------------------------")
            log.info(it.toString())
            val eventTime = System.currentTimeMillis()

            val (hourInicio, minInicio) = it.horaInicio.split(':', ignoreCase = false, limit = 2)
            val (hourFim, minFim) = it.horaFim.split(':', ignoreCase = false, limit = 2)


            val exams = Personal(listExamsPersonal)

            val contact = Contact("CELLPHONE", it.telefone)

            val patient = Patient(
                    progressId.toString(),
                    cip,
                    it.nome,
                    type,
                    genre,
                    "",
                    "",
                    "",
                    "",
                    insuranceId,
                    "",
                    "",
                    listOf(contact),
                    exams
            )

            val address = Address(
                    it.estado,
                    it.cidade,
                    it.cep,
                    it.rua,
                    it.numero,
                    it.bairro,
                    it.complemento
            )

            val homeCollect = HomeCollect(
                    typeHomeCollect,
                    roomIdHomeCollect,
                    address
            )

            val scheduleEvent = ScheduleEvent(
                    "progress",
                    brand.toString(),
                    brandIdDataProvider.toString(),
                    brandName,
                    locationId,
                    roomId,
                    roomName,
                    scheduleProgressId.toString(),
                    UUID.randomUUID().toString(),
                    LocalDate.of(2022,8,2).toString(),
                    LocalTime.of(hourInicio.toInt(), minInicio.toInt()).toString(),
                    LocalTime.of(hourFim.toInt(), minFim.toInt()).toString(),
                    statusFix,
                    listOf(patient),
                    homeCollect,
                    "",
                    eventTime.toString()
            )

            listAgendamentos.add(scheduleEvent)
        }


        val dataAgora = System.currentTimeMillis()
        val fileName = "JsonAgendamento$dataAgora.txt"

        var file = File(fileName)

        // create a new file
        val isNewFileCreated :Boolean = file.createNewFile()

        if(isNewFileCreated){
            println("$fileName is created successfully.")

            File(fileName).writeText(gson.toJson(listAgendamentos))

        } else{
            println("$fileName already exists.")
        }

        log.info("end flux.....")
        return "go"
    }


    fun readCsv(inputStream: InputStream): List<Agendamento> {
        val reader = inputStream.bufferedReader()
        val header = reader.readLine()
        return reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    CreatAgendamento(it.split(';'))
                }.toList()
    }

    private fun CreatAgendamento(list: List<String>): Agendamento {
        return Agendamento(
                list.get(0),
                list.get(1),
                list.get(2),
                list.get(3),
                list.get(4),
                list.get(5),
                list.get(6),
                list.get(7),
                list.get(8),
                list.get(9),
                list.get(10),
                list.get(11),
                list.get(12),
                list.get(13),
                list.get(14),
                list.get(15),
        )
    }


    fun readCsvMove(inputStream: InputStream): List<Movie> {
        val reader = inputStream.bufferedReader()
        val header = reader.readLine()
        return reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    val (year, rating, title) = it.split(';', ignoreCase = false, limit = 3)
                    Movie(year, rating, title)
                }.toList()
    }
}