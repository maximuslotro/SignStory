package com.kamesuta.mc.signpic.command;

import java.util.List;


import com.kamesuta.mc.signpic.entry.content.ContentId;
import com.kamesuta.mc.signpic.mode.CurrentMode;
import com.kamesuta.mc.signpic.util.ChatUtil;
import com.kamesuta.mc.signpic.util.WordUtil;
import com.kamesuta.mc.signpic.util.MathUtil;
import com.kamesuta.mc.signpic.gui.GuiMain;
import com.kamesuta.mc.signpic.Config;
import com.kamesuta.mc.signpic.Global_Vars;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class SignStoryCommand extends BaseCommand{
	@Override
	public String getCommandName() {
		return "SignStory";
	}
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/SignStory <place_sign, load_text, toggle_functionality>";
	}
	@Override
	public void processCommand(ICommandSender s, String[] args) {
		if (args.length == 0) {
			chatUsage(s);
		} 
		else if (args[0].toLowerCase().startsWith("p")) {
			if(Config.getConfig().defaultUsage.get()==false) {
				if(Global_Vars.Text!=null) {
					ChatUtil.chatNotify(s, "Testing");
					GuiMain.setContentId(Global_Vars.Text.get(Global_Vars.CurrentPage));
					CurrentMode.instance.setMode(CurrentMode.Mode.PLACE);
					CurrentMode.instance.setState(CurrentMode.State.PREVIEW, true);		
					}else {	
						ChatUtil.chatError(s, "No Text loaded first");
						ChatUtil.chatError(s, "Use (/SignStory load_text) to load text");
						}
				}else {	
					ChatUtil.chatError(s, "SignStory mode is disabled");
					ChatUtil.chatError(s, "Use (/SignStory toggle_functionality) to enable it");
					}
				
		} 
		else if (args[0].toLowerCase().startsWith("l")) {
			String text = "North of 53. A magic phrase. Spoken, mumbled or thought inwardly by thousands of souls venturing northward. An imaginary line, shown only on maps and labelled 53 degrees. It's presence indicated to highway travellers by road side signs. A division of territory as distinct in the mind as any international border. If you have not been North of 53, you have not been north!";
			Global_Vars.Text = WordUtil.Splitter(text, 15, 4);
			ChatUtil.chatNotify(s, "Text Set");
		}
		else if (args[0].toLowerCase().startsWith("s")) {
			if(Global_Vars.Text!=null) {
				if (MathUtil.isInt(args[1])) {
					if(Integer.parseInt(args[1])<= Global_Vars.Text.size()&&Integer.parseInt(args[1])> -1) {
						Global_Vars.CurrentPage =Integer.parseInt(args[1]);
						GuiMain.setContentId(Global_Vars.Text.get(Global_Vars.CurrentPage));
					}else {
						ChatUtil.chatError(s, "Page Number is too large or small!");
					}
				}else {
					ChatUtil.chatError(s, "Page Number must be a number!");
				}
			}else {	
				ChatUtil.chatError(s, "No Text loaded first");
				ChatUtil.chatError(s, "Use (/SignStory load_text) to load text");
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
			return CommandBase.getListOfStringsMatchingLastWord(args, "place_sign", "load_text", "select_page", "toggle_functionality");
		} else if (args.length == 2) {
			if (args[0].toLowerCase().startsWith("s")) {
				return CommandBase.getListOfStringsMatchingLastWord(args, "Select a page: 0 to "+Global_Vars.Text.size()+", to place");
			}
		}
		return null;
	}
	

}
