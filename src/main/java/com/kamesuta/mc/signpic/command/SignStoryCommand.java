package com.kamesuta.mc.signpic.command;

import java.util.List;

import com.kamesuta.mc.signpic.entry.content.ContentId;
import com.kamesuta.mc.signpic.mode.CurrentMode;
import com.kamesuta.mc.signpic.util.ChatBuilder;

import net.minecraft.command.ICommandSender;

public class SignStoryCommand extends BaseCommand{
	@Override
	public String getCommandName() {
		return "SignStory";
	}
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/SignStory";
	}
	@Override
	public void processCommand(ICommandSender s, String[] args) {
		ChatBuilder.create("Testing").sendPlayer(s);
		ContentId.from("This is a text to see if this works");
		CurrentMode.instance.setMode(CurrentMode.Mode.PLACE);
		CurrentMode.instance.setState(CurrentMode.State.PREVIEW, true);
		
	}
	/*
	@Override
	public List addTabCompletionOptions(final ICommandSender s, final String[] args) {
		return null;
	}*/

}
