package com.maximuslotro.mc.signpic.events;

import com.maximuslotro.mc.signpic.Global_Vars;
import com.maximuslotro.mc.signpic.Log;
import com.maximuslotro.mc.signpic.entry.EntryId;
import com.maximuslotro.mc.signpic.gui.GuiMain;
import com.maximuslotro.mc.signpic.mode.CurrentMode;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class EventListener {
	@SubscribeEvent
    public void onPlayerLeftServer(ClientDisconnectionFromServerEvent event) {
		Global_Vars.CurrentPage = 0;
		GuiMain.setContentId(EntryId.blank);
		Global_Vars.Text.clear();
		Global_Vars.Text = null;
		CurrentMode.instance.setMode(CurrentMode.Mode.NONE);
		CurrentMode.instance.setState(CurrentMode.State.PREVIEW, false);
		Log.logDefault("Server Exited, Cleared Loaded Text");
    }

}
