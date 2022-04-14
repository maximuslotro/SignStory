package com.maximuslotro.mc.signpic.command;

import java.util.List;

import com.maximuslotro.mc.signpic.entry.EntryId;
import com.maximuslotro.mc.signpic.entry.content.ContentId;
import com.maximuslotro.mc.signpic.mode.CurrentMode;
import com.maximuslotro.mc.signpic.util.ChatUtil;
import com.maximuslotro.mc.signpic.util.WordUtil;
import com.maximuslotro.mc.signpic.util.MathUtil;
import com.maximuslotro.mc.signpic.gui.GuiMain;
import com.maximuslotro.mc.signpic.gui.file.McUiTextSelect;
import com.maximuslotro.mc.signpic.Config;
import com.maximuslotro.mc.signpic.Global_Vars;
import com.maximuslotro.mc.signpic.Log;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class SignStoryCommand extends BaseCommand{
	@Override
	public String getCommandName() {
		return "SignStory";
	}
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return ("/SignStory <"+I18n.format("signstory.command.subcommand.load")+", " +I18n.format("signstory.command.subcommand.select")+", " +I18n.format("signstory.command.subcommand.toggle"));
	}
	@Override
	public void processCommand(ICommandSender s, String[] args) {
		if (args.length == 0) {
			chatUsage(s);
		} 
		else if (args[0].toLowerCase().startsWith(I18n.format("signstory.command.subcommand.load.letter"))) {
			if(Config.getConfig().defaultUsage.get()==false) {
				//String text = "If you have not been North of 53, you have not been north!";
				//Global_Vars.Text = WordUtil.Splitter(text, 15, 4);
				McUiTextSelect.instance.setVisible(!McUiTextSelect.instance.isVisible(),true);
			}else {
				ChatUtil.chatError(s, I18n.format("signstory.command.enabled.not.please"));
				ChatUtil.chatNotify(s, I18n.format("signstory.command.toggle.info"));
			}
		}
		else if (args[0].toLowerCase().startsWith(I18n.format("signstory.command.subcommand.select.letter"))) {
			if(Config.getConfig().defaultUsage.get()==false) {
				if(Global_Vars.Text!=null) {
					if(args.length > 1) {
						if (MathUtil.isInt(args[1])) {
							if((Integer.valueOf(args[1])-1)<=(Global_Vars.Text.size()-1)&&(Integer.valueOf(args[1])-1)> -1) {
								Global_Vars.CurrentPage =(Integer.valueOf(args[1])-1);
								GuiMain.setContentId(Global_Vars.Text.get(Global_Vars.CurrentPage));
								ChatUtil.chatConfirm(s, I18n.format("signstory.command.select.page.selected")+(Global_Vars.CurrentPage+1));
							}else {
								ChatUtil.chatError(s, I18n.format("signstory.command.select.page.error"));
							}
						}else {
							ChatUtil.chatConfirm(s, I18n.format("signstory.command.select.page.info")+(Global_Vars.Text.size()));
							ChatUtil.chatConfirm(s, I18n.format("signstory.command.select.info"));
						}
					}else {
						ChatUtil.chatConfirm(s, I18n.format("signstory.command.select.page.info")+(Global_Vars.Text.size()));
						ChatUtil.chatConfirm(s, I18n.format("signstory.command.select.info"));
					}
				}else {	
					ChatUtil.chatError(s, I18n.format("signstory.command.text.notloaded"));
					ChatUtil.chatError(s, I18n.format("signstory.command.text.loadcommand"));
					}
			}else {
				ChatUtil.chatError(s, I18n.format("signstory.command.enabled.not.please"));
				ChatUtil.chatNotify(s, I18n.format("signstory.command.toggle.info"));
			}
		}
		else if (args[0].toLowerCase().startsWith(I18n.format("signstory.command.subcommand.toggle.letter"))) {
			Boolean config = Config.getConfig().defaultUsage.get();
			Config.getConfig().defaultUsage.set(!config);
			if(!config){
				ChatUtil.chatConfirm(s, I18n.format("signstory.command.enabled.signpicture.toggled.on"));
				ChatUtil.chatError(s, I18n.format("signstory.command.enabled.signstory.toggled.off"));
				CurrentMode.instance.setMode(CurrentMode.Mode.NONE);
				CurrentMode.instance.setState(CurrentMode.State.PREVIEW, false);
				GuiMain.setContentId(EntryId.blank);
				Global_Vars.CurrentPage = 0;
			}
			else { 
				ChatUtil.chatError(s, I18n.format("signstory.command.enabled.signpicture.toggled.off"));
				ChatUtil.chatConfirm(s, I18n.format("signstory.command.enabled.signstory.toggled.on"));
				ChatUtil.chatNotify(s, I18n.format("signstory.command.text.loadcommand"));
				CurrentMode.instance.setMode(CurrentMode.Mode.NONE);
				CurrentMode.instance.setState(CurrentMode.State.PREVIEW, false);
				GuiMain.setContentId(EntryId.blank);
				Global_Vars.CurrentPage = 0;
			}
		}
		else { chatUsage(s); }
	}
	@Override
	public List addTabCompletionOptions(final ICommandSender s, final String[] args) {
		if (args.length == 1) {
			return CommandBase.getListOfStringsMatchingLastWord(args, I18n.format("signstory.command.subcommand.load"), I18n.format("signstory.command.subcommand.select"), I18n.format("signstory.command.subcommand.toggle"));
		} else if (args.length == 2) {
			if (args[0].toLowerCase().startsWith(I18n.format("signstory.command.subcommand.select.letter"))) {
				return CommandBase.getListOfStringsMatchingLastWord(args, "");
			}
		}
		return null;
	}
	

}
