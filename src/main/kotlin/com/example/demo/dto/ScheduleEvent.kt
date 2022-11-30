package com.example.demo.dto

import java.io.Serializable

data class ScheduleEvent (
        val origin: String,
        val brandId: String,
        val brandIdDataProvider: String,
        val brandName: String,
        val locationId: String,
        val roomId: String,
        val roomName: String,
        val scheduleProgressId: String,
        val scheduleUuid: String,
        val date: String,
        val hourBegin: String,
        val hourEnd: String,
        val status: String,
        val patients: List<Patient>,
        val homeCollect: HomeCollect,
        val note: String,
        val eventTime: String
) : Serializable