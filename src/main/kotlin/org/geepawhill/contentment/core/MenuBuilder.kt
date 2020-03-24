/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009, 2010, 2011, 2012, 2013, 2014, 2015 Caprica Software Limited.
 */
package org.geepawhill.contentment.core

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.input.KeyCombination

/**
 * Helper class to create the application main menu.
 */
internal object MenuBuilder {
    /**
     * Create the application menu.
     *
     *
     * The menu is non-functional, it is currently used for demo purposes only.
     *
     * @return menu
     */
    fun createMenu(application: JavaFXDirectRenderingTest): MenuBar {
        val mediaPlayer = application.mediaPlayer
        val menuBar = MenuBar()
        val mediaMenu = Menu("_Media")
        mediaMenu.isMnemonicParsing = true
        val mediaOpenFileMenuItem = MenuItem("_Open File")
        mediaOpenFileMenuItem.accelerator = KeyCombination.keyCombination("Ctrl+O")
        mediaOpenFileMenuItem.isMnemonicParsing = true
        val mediaQuitMenuItem = MenuItem("_Quit")
        mediaQuitMenuItem.accelerator = KeyCombination.keyCombination("Ctrl+Q")
        mediaQuitMenuItem.isMnemonicParsing = true
        mediaMenu.items.add(mediaOpenFileMenuItem)
        mediaMenu.items.add(SeparatorMenuItem())
        mediaMenu.items.add(mediaQuitMenuItem)
        menuBar.menus.add(mediaMenu)
        val playbackMenu = Menu("P_layback")
        mediaMenu.isMnemonicParsing = true
        val playbackJumpForwardMenuItem = MenuItem("_Jump Forward")
        playbackJumpForwardMenuItem.isMnemonicParsing = true
        playbackJumpForwardMenuItem.accelerator = KeyCombination.keyCombination("Ctrl+Right")
        val playbackJumpBackwardMenuItem = MenuItem("Jump Bac_kward")
        playbackJumpBackwardMenuItem.isMnemonicParsing = true
        playbackJumpBackwardMenuItem.accelerator = KeyCombination.keyCombination("Ctrl+Left")
        val playbackPlayMenuItem = MenuItem("_Play")
        playbackPlayMenuItem.isMnemonicParsing = true
        val playbackPauseMenuItem = MenuItem("Pa_use")
        playbackPauseMenuItem.isMnemonicParsing = true
        val playbackStopMenuItem = MenuItem("_Stop")
        playbackStopMenuItem.isMnemonicParsing = true
        playbackMenu.items.add(playbackJumpForwardMenuItem)
        playbackMenu.items.add(playbackJumpBackwardMenuItem)
        playbackMenu.items.add(SeparatorMenuItem())
        playbackMenu.items.add(playbackPlayMenuItem)
        playbackMenu.items.add(playbackPauseMenuItem)
        playbackMenu.items.add(playbackStopMenuItem)
        menuBar.menus.add(playbackMenu)
        val audioMenu = Menu("_Audio")
        audioMenu.isMnemonicParsing = true
        val audioMuteMenuItem = CheckMenuItem("_Mute")
        audioMuteMenuItem.accelerator = KeyCombination.keyCombination("Ctrl+M")
        audioMuteMenuItem.isMnemonicParsing = true
        // Mute is a bit tricksy, sadly
        audioMenu.items.add(audioMuteMenuItem)

//        menuBar.getMenus().add(audioMenu);
        val toolsMenu = Menu("Tool_s")
        toolsMenu.isMnemonicParsing = true
        val toolsVideoAdjustmentMenuItem = CheckMenuItem("Adjust _Video")
        toolsVideoAdjustmentMenuItem.isMnemonicParsing = true
        toolsMenu.items.add(toolsVideoAdjustmentMenuItem)
        menuBar.menus.add(toolsMenu)
        val viewMenu = Menu("V_iew")
        viewMenu.isMnemonicParsing = true
        val viewAlwaysOnTopMenuItem = CheckMenuItem("Always on _top")
        viewAlwaysOnTopMenuItem.isMnemonicParsing = true
        val viewMinimalInterfaceMenuItem = CheckMenuItem("Mi_nimal Interface")
        viewMinimalInterfaceMenuItem.isMnemonicParsing = true
        viewMinimalInterfaceMenuItem.accelerator = KeyCombination.keyCombination("Ctrl+H")
        val viewFullScreenMenuItem = MenuItem("_Fullscreen Interface")
        viewFullScreenMenuItem.isMnemonicParsing = true
        viewFullScreenMenuItem.accelerator = KeyCombination.keyCombination("F11")
        val viewStatsOverlayMenuItem = CheckMenuItem("_Statistics Overlay")
        viewStatsOverlayMenuItem.isMnemonicParsing = true
        viewStatsOverlayMenuItem.accelerator = KeyCombination.keyCombination("Ctrl+S")
        viewStatsOverlayMenuItem.isSelected = true
        val viewAnimationOverlayMenuItem = CheckMenuItem("_Animation Overlay")
        viewAnimationOverlayMenuItem.isMnemonicParsing = true
        viewAnimationOverlayMenuItem.accelerator = KeyCombination.keyCombination("Ctrl+A")
        viewAnimationOverlayMenuItem.isSelected = true
        viewMenu.items.add(viewAlwaysOnTopMenuItem)
        viewMenu.items.add(SeparatorMenuItem())
        viewMenu.items.add(viewMinimalInterfaceMenuItem)
        viewMenu.items.add(viewFullScreenMenuItem)
        viewMenu.items.add(SeparatorMenuItem())
        viewMenu.items.add(viewStatsOverlayMenuItem)
        viewMenu.items.add(viewAnimationOverlayMenuItem)
        menuBar.menus.add(viewMenu)
        val helpMenu = Menu("_Help")
        helpMenu.isMnemonicParsing = true
        val helpAboutMenuItem = MenuItem("_About")
        helpAboutMenuItem.accelerator = KeyCombination.keyCombination("Shift+F1")
        helpAboutMenuItem.isMnemonicParsing = true
        helpMenu.items.add(helpAboutMenuItem)
        menuBar.menus.add(helpMenu)
        mediaQuitMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> System.exit(0) }
        mediaOpenFileMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> application.openFile() }
        playbackJumpForwardMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> mediaPlayer.controls().skipTime(10000) }
        playbackJumpBackwardMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> mediaPlayer.controls().skipTime(-10000) }
        playbackPlayMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> mediaPlayer.controls().play() }
        playbackPauseMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> mediaPlayer.controls().setPause(true) }
        playbackStopMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> mediaPlayer.controls().stop() }
        audioMuteMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> mediaPlayer.audio().isMute = audioMuteMenuItem.isSelected }
        toolsVideoAdjustmentMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> application.adjustVideo(toolsVideoAdjustmentMenuItem.isSelected) }
        viewAlwaysOnTopMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> application.toggleAlwaysOnTop() }
        viewMinimalInterfaceMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> application.toggleMinimalInterface(!viewMinimalInterfaceMenuItem.isSelected) }
        viewFullScreenMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> application.toggleFullScreen() }
        viewStatsOverlayMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> application.toggleStatsOverlay(viewStatsOverlayMenuItem.isSelected) }
        viewAnimationOverlayMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> application.toggleAnimationOverlay(viewAnimationOverlayMenuItem.isSelected) }
        helpAboutMenuItem.onAction = EventHandler { actionEvent: ActionEvent? -> application.showAbout() }
        return menuBar
    }
}