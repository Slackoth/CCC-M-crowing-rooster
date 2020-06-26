package com.cccm.crowingrooster.data.unitlocalized

import androidx.room.ColumnInfo

data class BulbasaurKantoPokemon(
    @ColumnInfo(name = "name")
    override val name: String,
    @ColumnInfo(name = "url")
    override val url: String
) : UnitSpecificKantoPokemon