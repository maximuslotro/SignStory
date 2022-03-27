package com.maximuslotro.mc.signpic.command;

import java.util.List;

import com.maximuslotro.mc.signpic.entry.EntryId;
import com.maximuslotro.mc.signpic.entry.content.ContentId;
import com.maximuslotro.mc.signpic.mode.CurrentMode;
import com.maximuslotro.mc.signpic.util.ChatUtil;
import com.maximuslotro.mc.signpic.util.WordUtil;
import com.maximuslotro.mc.signpic.util.MathUtil;
import com.maximuslotro.mc.signpic.gui.GuiMain;
import com.maximuslotro.mc.signpic.gui.file.McUiUpload;
import com.maximuslotro.mc.signpic.Config;
import com.maximuslotro.mc.signpic.Global_Vars;
import com.maximuslotro.mc.signpic.Log;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class SignStoryCommand extends BaseCommand{
	@Override
	public String getCommandName() {
		return "SignStory";
	}
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/SignStory <place_sign, load_text, select_page, toggle_functionality>";
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
			if(Config.getConfig().defaultUsage.get()==false) {
				//String text = "If you have not been North of 53, you have not been north!";
				//Global_Vars.Text = WordUtil.Splitter(text, 15, 4);
				McUiUpload.instance.setVisible(!McUiUpload.instance.isVisible());
				ChatUtil.chatNotify(s, "Text Set");
				Log.logDefault("Text Set from String");
			}else {ChatUtil.chatError(s, "Please Enable SignStory First!");}
		}
		else if (args[0].toLowerCase().startsWith("s")) {
			if(Global_Vars.Text!=null) {
				if(args.length > 1) {
					if (MathUtil.isInt(args[1])) {
						if(Integer.valueOf(args[1])<=(Global_Vars.Text.size()-1)&&Integer.valueOf(args[1])> -1) {
							Global_Vars.CurrentPage =Integer.valueOf(args[1]);
							GuiMain.setContentId(Global_Vars.Text.get(Global_Vars.CurrentPage));
							ChatUtil.chatConfirm(s, "Set current page to: "+Global_Vars.CurrentPage);
						}else {
							ChatUtil.chatError(s, "Page Number is too large or small!");
						}
					}else {
						ChatUtil.chatConfirm(s, "Select a page: 0 to "+(Global_Vars.Text.size()-1)+", to place");
						ChatUtil.chatConfirm(s, "Use (/SignStory select_page #) to select a page");
					}
				}else {
					ChatUtil.chatConfirm(s, "Select a page: 0 to "+(Global_Vars.Text.size()-1)+", to place");
					ChatUtil.chatConfirm(s, "Use (/SignStory select_page #) to select a page");
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
				ChatUtil.chatConfirm(s, "Enabled SignPicture Usage");
				ChatUtil.chatError(s, "Disabled SignStory Usage");
				CurrentMode.instance.setMode(CurrentMode.Mode.NONE);
				CurrentMode.instance.setState(CurrentMode.State.PREVIEW, false);
				GuiMain.setContentId(EntryId.blank);

			}
			else { 
				ChatUtil.chatError(s, "Disabled SignPicture Usage");
				ChatUtil.chatConfirm(s, "Enabled SignStory Usage");
				ChatUtil.chatNotify(s, "Use (/SignStory load_text) to load text");
				CurrentMode.instance.setState(CurrentMode.State.PREVIEW, true);
				GuiMain.setContentId(EntryId.blank);
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
				return CommandBase.getListOfStringsMatchingLastWord(args, "");
			}
		}
		return null;
	}
	

}
