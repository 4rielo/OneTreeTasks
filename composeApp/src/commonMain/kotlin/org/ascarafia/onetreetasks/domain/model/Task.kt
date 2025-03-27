package org.ascarafia.onetreetasks.domain.model

data class Task(
    val id: String,
    val title: String,
    val body: String,
    val isCompleted: Boolean,
    val latitude: Double? = null,
    val longitude: Double? = null
)