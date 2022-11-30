package com.example.demo.controller

import com.example.demo.service.ReadCsvService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/csv")
class CsvCall(
        private val readCsvService: ReadCsvService
) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping()
    fun generateFinalReport(): String {
        log.info("call generateFinalReport ")
        readCsvService.generateJsonOfCsv()
         return "ok"
    }
}