package com.cccm.crowingrooster.data.unitlocalized

import androidx.room.ColumnInfo

data class CharmanderKantoPokemon(
    @ColumnInfo(name = "name")
    override val name: String,
    @ColumnInfo(name = "url")
    override val url: String
) : UnitSpecificKantoPokemon