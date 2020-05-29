package com.cccm.crowingrooster

import android.view.Menu

public interface CommunicationInterface {
    public fun hideTopBar()
    public fun showTopBar()
    public fun drawerLocked(Locked: Boolean)

    //public fun addMoreOptions(menuId: Int, menu: Menu?)
}