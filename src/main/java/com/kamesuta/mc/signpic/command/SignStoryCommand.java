package com.kamesuta.mc.signpic.command;

import java.util.List;


import com.kamesuta.mc.signpic.entry.content.ContentId;
import com.kamesuta.mc.signpic.mode.CurrentMode;
import com.kamesuta.mc.signpic.util.ChatUtil;
import com.kamesuta.mc.signpic.util.WordUtil;
import com.kamesuta.mc.signpic.gui.GuiMain;
import com.kamesuta.mc.signpic.Config;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class SignStoryCommand extends BaseCommand{
	@Override
	public String getCommandName() {
		return "SignStory";
	}
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/SignStory <place_sign, toggle_functionality>";
	}
	@Override
	public void processCommand(ICommandSender s, String[] args) {
		if (args.length == 0) {
			chatUsage(s);
		} 
		else if (args[0].toLowerCase().startsWith("p")) {
			if(Config.getConfig().defaultUsage.get()==false) {
				ChatUtil.chatNotify(s, "Testing");
				//ContentId.from("This is a text to see if this works");
				String text = "This Great Experiment is for the good of all humanity";
				String fixed_lines = WordUtil.Splitter(text, 15, 4);
				GuiMain.setContentId(fixed_lines);
				CurrentMode.instance.setMode(CurrentMode.Mode.PLACE);
				CurrentMode.instance.setState(CurrentMode.State.PREVIEW, true);
				CurrentMode.instance.setState(CurrentMode.State.SEE, true);			
				}else {	
					ChatUtil.chatError(s, "SignStory mode is disabled");
					ChatUtil.chatError(s, "Use (/SignStory toggle_functionality) to enable it");
				}
		} 
		else if (args[0].toLowerCase().startsWith("t")) {
			Boolean config = Config.getConfig().defaultUsage.get();
			Config.getConfig().defaultUsage.set(!config);
			if(!config){
				ChatUtil.chatNotify(s, "Enabled SignPicture Usage");
				ChatUtil.chatError(s, "Disabled SignStory Usage");
				CurrentMode.instance.setMode(CurrentMode.Mode.NONE);
				CurrentMode.instance.setState(CurrentMode.State.PREVIEW, false);
			}
			else { 
				ChatUtil.chatError(s, "Disabled SignPicture Usage");
				ChatUtil.chatNotify(s, "Enabled SignStory Usage");
			}
		}
		else { chatUsage(s); }
	}
	@Override
	public List addTabCompletionOptions(final ICommandSender s, final String[] args) {
		if (args.length == 1) {
			return CommandBase.getListOfStringsMatchingLastWord(args, "place_sign", "toggle_functionality");
		} /*else if (args.length == 2) {
			if (args[0].toLowerCase().startsWith("v") || args[0].toLowerCase().startsWith("t")) {
				return this.tabCompleteUsernames(args);
			} else if (args[0].toLowerCase().startsWith("d")) {
				return CommandBase.getListOfStringsMatchingLastWord(args, "all");
			}
		}*/
		return null;
	}

}
