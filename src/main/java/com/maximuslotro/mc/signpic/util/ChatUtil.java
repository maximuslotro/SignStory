package com.maximuslotro.mc.signpic.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ChatUtil {
	public static List<String> constructHelp(final String header, final String[] commands, final String[] help, final char colour1, final char colour2) {
		return constructHelp(header, commands, help, colour1, colour2, 1, 8);
	}

	public static List<String> constructHelp(final String header, final String[] commands, final String[] help, final char colour1, final char colour2, final int page) {
		return constructHelp(header, commands, help, colour1, colour2, page, 8);
	}

	public static List<String> constructHelp(final String header, final String[] commands, final String[] help, final char colour1, final char colour2, final int page, final int entriesPerPage) {
		final List<String> lines = new ArrayList<String>();

		final int totalPages = (int) Math.ceil((double) commands.length / entriesPerPage);
		final int firstIndex = (page - 1) * entriesPerPage;
		int lastIndex = firstIndex + entriesPerPage;

		if (page > totalPages || page < 1) {
			return lines;
		}

		if (lastIndex > commands.length) {
			lastIndex = commands.length;
		}

		lines.add("");

		lines.add("&" + colour2 + "--- " + "&" + colour1 + "&l" + header + " &" + colour1 +
				"[Page &" + colour2 + page + " &" + colour1 + "of &" + colour2 + totalPages + "&" + colour1 + "] &" + colour2 + "---");

		for (int c = firstIndex; c < lastIndex; c++) {
			lines.add("&" + colour1 + "&o" + "/" + commands[c] + "&" + colour2 + " - " + help[c]);
		}

		lines.add("");

		return lines;
	}




	public static void chatError(final ICommandSender sender, final String msg) {
		final ChatComponentText text = new ChatComponentText(msg);
		text.getChatStyle().setColor(EnumChatFormatting.RED);
		chat(sender, text);
	}

	public static void chatConfirm(final ICommandSender sender, final String msg) {
		final ChatComponentText text = new ChatComponentText(msg);
		text.getChatStyle().setColor(EnumChatFormatting.GREEN);
		chat(sender, text);
	}

	public static void chatNotify(final ICommandSender sender, final String msg) {
		final ChatComponentText text = new ChatComponentText(msg);
		text.getChatStyle().setColor(EnumChatFormatting.GOLD);
		chat(sender, text);
	}

	public static void chat(final ICommandSender sender, final String msg) {
		chat(sender, formatString(msg));
	}

	public static void chat(final ICommandSender sender, final IChatComponent msg) {
		if (sender != null) {
			sender.addChatMessage(msg);
		}
	}


	public static String removeColourCoding(String msg, final char code) {
		if (msg.length() < 2) {
			return msg;
		}

		char[] chars = msg.toCharArray();

		for (int c = 0; c < chars.length; c++) {
			if (chars[c] == code && ("0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(chars[(c + 1)])) > -1) {
				msg = msg.substring(0, c) + msg.substring(c + 2);
				chars = msg.toCharArray();
				c--;
			}
		}

		return msg;
	}

	public static String formatSymbols(final String msg) {
		if (msg.length() < 2) {
			return msg;
		}

		final char[] chars = msg.toCharArray();

		for (int c = 0; c < chars.length - 1; c++) {
			if (chars[c] == '&' && ("0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(chars[(c + 1)])) > -1) {
				chars[c] = '\u00A7';
				chars[(c + 1)] = Character.toLowerCase(chars[(c + 1)]);
			}
		}

		return new String(chars);
	}

	public static EnumChatFormatting getChatFormatting(final char code) {
		switch (code) {
		case '0':
			return EnumChatFormatting.BLACK;
		case '1':
			return EnumChatFormatting.DARK_BLUE;
		case '2':
			return EnumChatFormatting.DARK_GREEN;
		case '3':
			return EnumChatFormatting.DARK_AQUA;
		case '4':
			return EnumChatFormatting.DARK_RED;
		case '5':
			return EnumChatFormatting.DARK_PURPLE;
		case '6':
			return EnumChatFormatting.GOLD;
		case '7':
			return EnumChatFormatting.GRAY;
		case '8':
			return EnumChatFormatting.DARK_GRAY;
		case '9':
			return EnumChatFormatting.BLUE;
		case 'a':
			return EnumChatFormatting.GREEN;
		case 'b':
			return EnumChatFormatting.AQUA;
		case 'c':
			return EnumChatFormatting.RED;
		case 'd':
			return EnumChatFormatting.LIGHT_PURPLE;
		case 'e':
			return EnumChatFormatting.YELLOW;
		case 'f':
			return EnumChatFormatting.WHITE;
		case 'r':
			return EnumChatFormatting.RESET;
		case 'k':
			return EnumChatFormatting.OBFUSCATED;
		case 'l':
			return EnumChatFormatting.BOLD;
		case 'm':
			return EnumChatFormatting.STRIKETHROUGH;
		case 'n':
			return EnumChatFormatting.UNDERLINE;
		case 'o':
			return EnumChatFormatting.ITALIC;
		default:
			return null;
		}
	}

	public static String getHex(final char code) {
		return getHex(getChatFormatting(code));
	}

	public static String getHex(final EnumChatFormatting ecf) {
		switch (ecf) {
		case AQUA:
			return "#55FFFF";
		case BLACK:
			return "#000000";
		case BLUE:
			return "#5555FF";
		case DARK_AQUA:
			return "#00AAAA";
		case DARK_BLUE:
			return "#0000AA";
		case DARK_GRAY:
			return "#555555";
		case DARK_GREEN:
			return "#00AA00";
		case DARK_PURPLE:
			return "#AA00AA";
		case DARK_RED:
			return "#AA0000";
		case GOLD:
			return "#FFAA00";
		case GRAY:
			return "#AAAAAA";
		case GREEN:
			return "#55FF55";
		case LIGHT_PURPLE:
			return "#FF55FF";
		case RED:
			return "#FF5555";
		case WHITE:
			return "#FFFFFF";
		case YELLOW:
			return "#FFFF55";
		default:
			return null;
		}
	}

	public static ChatComponentText formatString(String msg) {
		final ChatComponentText mainText = new ChatComponentText("");

		final HashMap<Integer, List<Character>> formatCodes = new HashMap<Integer, List<Character>>();

		msg = "&r" + msg;

		for (int c = 0; c < msg.length() - 1; c++) {
			final char current = msg.charAt(c);
			final char next = msg.charAt(c + 1);

			if (current == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(next) != -1) {
				msg = msg.substring(0, c) + msg.substring(c + 2, msg.length());

				if (formatCodes.containsKey(c)) {
					formatCodes.get(c).add(next);
				} else {
					final List<Character> newList = new ArrayList<Character>();
					newList.add(next);
					formatCodes.put(c, newList);
				}

				c--;
			}
		}

		int startIndex = 0;
		int c = 0;
		final Object[] indexes = formatCodes.keySet().toArray();
		Arrays.sort(indexes);
		for (final Object index : indexes) {
			final List<Character> codes = formatCodes.get(index);
			ChatComponentText text;

			if (c == formatCodes.size() - 1) {
				text = new ChatComponentText(msg.substring(startIndex));
			} else {
				text = new ChatComponentText(msg.substring(startIndex, (Integer) indexes[c + 1]));
			}

			for (final char code : codes) {
				switch (code) {
				case 'k':
					text.getChatStyle().setObfuscated(true);
					break;
				case 'l':
					text.getChatStyle().setBold(true);
					break;
				case 'm':
					text.getChatStyle().setStrikethrough(true);
					break;
				case 'n':
					text.getChatStyle().setUnderlined(true);
					break;
				case 'o':
					text.getChatStyle().setItalic(true);
					break;
				default:
					text.getChatStyle().setColor(getChatFormatting(code));
				}
			}

			mainText.appendSibling(text);

			if (c < indexes.length - 1) {
				startIndex = (Integer) indexes[c + 1];
				c++;
			}
		}

		return mainText;
	}

	/**
	 * @return length of string disregarding formatting codes
	 */
	public static int actualLength(final String msg) {
		final char[] chars = msg.toCharArray();
		int disregarded = 0;

		for (int c = 0; c < chars.length - 1; c++) {
			if (chars[c] == '&' && ("0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(chars[(c + 1)])) > -1) {
				disregarded = disregarded + 2;
			}
		}

		return msg.length() - disregarded;
	}
}
