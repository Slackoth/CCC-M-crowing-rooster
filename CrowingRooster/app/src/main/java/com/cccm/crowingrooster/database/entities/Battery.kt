package com.cccm.crowingrooster.database.entities

import androidx.room.*

data class Dimensions(
    val height: Double,
    val width: Double,
    val length: Double
)

@Entity(tableName = "battery", foreignKeys = [
    //TODO: Battery-Quality
    ForeignKey(
        entity = Quality::class,
        parentColumns = ["quality_id"],
        childColumns = ["battery_quality"]
        //onDelete = ForeignKey.CASCADE
    ),
    //TODO: Battery-Polarity
    ForeignKey(
        entity = Polarity::class,
        parentColumns = ["polarity_id"],
        childColumns = ["battery_polarity"]
        //onDelete = ForeignKey.CASCADE
    )
])
data class Battery(
    @PrimaryKey(autoGenerate = true) val battery_id: Int,
    @Embedded val dimensions: Dimensions,
    @ColumnInfo val reserve_capacity: Int,
    @ColumnInfo val amperage: Int,
    @ColumnInfo val cold_cranking_amperes: Int,
    @ColumnInfo val battery_quality: Int,
    @ColumnInfo val battery_polarity: Int
)