package com.kamesuta.mc.signpic.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class BaseCommand implements ICommand {
	public String[] getAliases() {
		return null;
	}

	//@Override
	public int compareTo(final Object object) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return null;
	}

	public String getCommandUsage() {
		return null;
	}

	@Override
	public String getCommandUsage(final ICommandSender sender) {
		return getCommandUsage();
	}

	@Override
	public List<String> getCommandAliases() {
		final String[] aliases = getAliases();

		if (aliases == null) {
			return null;
		} else {
			final List<String> list = new ArrayList<String>();

			for (final String alias : aliases) {
				list.add(alias);
			}

			return list;
		}
	}

	@Override
	public void processCommand(final ICommandSender s, final String[] args) {

	}

	@Override
	public boolean canCommandSenderUseCommand(final ICommandSender sender) {
		return true;
	}

	public boolean tabCompleteNames() {
		return false;
	}

	@Override
	public List addTabCompletionOptions(final ICommandSender s, final String[] args) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(final String[] args, final int integer) {
		return false;
	}
}
