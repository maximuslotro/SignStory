package com.maximuslotro.mc.signpic.util;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.maximuslotro.mc.signpic.Client;
import com.maximuslotro.mc.signpic.Global_Vars;
import com.maximuslotro.mc.signpic.Log;
import com.maximuslotro.mc.signpic.gui.GuiMain;
import com.maximuslotro.mc.signpic.http.upload.UploadApiUtil;
import com.maximuslotro.mc.signpic.http.upload.UploadCallback;
import com.maximuslotro.mc.signpic.http.upload.UploadRequest;
import com.maximuslotro.mc.signpic.mode.CurrentMode;
import com.maximuslotro.mc.signpic.state.State;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;

public class FileUtilitiy {
	public static boolean transfer(final @Nonnull Transferable transferable, final @Nullable UploadCallback after) {
		try {
			if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				final String id = (String) transferable.getTransferData(DataFlavor.stringFlavor);
				if (id!=null) {
					GuiMain.setContentId(id);
					if (after!=null)
						after.onDone(id);
				}
			} else if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				final List<?> droppedFiles = (List<?>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
				for (final Object obj : droppedFiles)
					if (obj instanceof File) {
						final File file = (File) obj;
						UploadApiUtil.upload(UploadRequest.fromFile(file, new State()), after);
					}
			} else if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
				final BufferedImage bi = (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
				if (bi!=null)
					try {
						uploadImage(bi, after);
					} catch (final IOException e) {
						Log.notice(I18n.format("signpic.gui.notice.paste.error", e));
						return false;
					}
			} else {
				Log.notice(I18n.format("signpic.gui.notice.paste.typeunsupported"));
				return false;
			}
		} catch (final IOException e) {
			Log.notice(I18n.format("signpic.gui.notice.paste.error", e));
			return false;
		} catch (final UnsupportedFlavorException e) {
			Log.notice(I18n.format("signpic.gui.notice.paste.unsupported", e));
		}
		return true;
	}

	public static void uploadImage(@Nonnull final BufferedImage image, final @Nullable UploadCallback after) throws IOException {
		final File tmp = Client.getLocation().createCache("paste");
		ImageIO.write(image, "png", tmp);
		UploadApiUtil.upload(UploadRequest.fromFile(tmp, new State()), new UploadCallback() {
			@Override
			public void onDone(final String str) {
				FileUtils.deleteQuietly(tmp);
				if (after!=null)
					after.onDone(str);
			}
		});
	}
	public static void loadTextFile(File file) {
		String content = "";
		try {
			content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.fail"), EnumChatFormatting.RED));
		}
		boolean used= false;
		if(!content.contains("&")&&used==false) {
			//content = content.replace("\n", " ");
			content = content.replace("\r", "");
			content = content.replace("\n", "& ");
			//content = content.replace("\r\n", " ");
			try {
				Global_Vars.Text = WordUtil.Splitter(content, 15, 4, '&');
				ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.success"), EnumChatFormatting.GREEN));
				setStuff();
				}catch(Exception e) {
					ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.fail"), EnumChatFormatting.RED));
				}
			used=true;
		}
		if(!content.contains("%")&&used==false) {
			//content = content.replace("\n", " ");
			content = content.replace("\r", "");
			content = content.replace("\n", "% ");
			//content = content.replace("\r\n", " ");
			try {
				Global_Vars.Text = WordUtil.Splitter(content, 15, 4, '%');
				ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.success"), EnumChatFormatting.GREEN));
				setStuff();
				}catch(Exception e) {
					ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.fail"), EnumChatFormatting.RED));
				}
			used=true;
		}
		if(!content.contains("$")&&used==false) {
			//content = content.replace("\n", " ");
			content = content.replace("\r", "");
			content = content.replace("\n", "$ ");
			//content = content.replace("\r\n", " ");
			try {
				Global_Vars.Text = WordUtil.Splitter(content, 15, 4, '$');
				ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.success"), EnumChatFormatting.GREEN));
				setStuff();
				}catch(Exception e) {
					ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.fail"), EnumChatFormatting.RED));
				}
			used=true;
		}
		if(!content.contains("@")&&used==false) {
			//content = content.replace("\n", " ");
			content = content.replace("\r", "");
			content = content.replace("\n", "@ ");
			//content = content.replace("\r\n", " ");
			try {
				Global_Vars.Text = WordUtil.Splitter(content, 15, 4, '@');
				ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.success"), EnumChatFormatting.GREEN));
				setStuff();
				}catch(Exception e) {
					ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.fail"), EnumChatFormatting.RED));
				}
			used=true;
		}
		if(!content.contains("{")&&used==false) {
			//content = content.replace("\n", " ");
			content = content.replace("\r", "");
			content = content.replace("\n", "{ ");
			//content = content.replace("\r\n", " ");
			try {
				Global_Vars.Text = WordUtil.Splitter(content, 15, 4, '{');
				ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.success"), EnumChatFormatting.GREEN));
				setStuff();
				}catch(Exception e) {
					ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.fail"), EnumChatFormatting.RED));
				}
			used=true;
		}
		if(!content.contains("}")&&used==false) {
			//content = content.replace("\n", " ");
			content = content.replace("\r", "");
			content = content.replace("\n", "} ");
			//content = content.replace("\r\n", " ");
			try {
				Global_Vars.Text = WordUtil.Splitter(content, 15, 4, '}');
				ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.success"), EnumChatFormatting.GREEN));
				setStuff();
				}catch(Exception e) {
					ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.fail"), EnumChatFormatting.RED));
				}
			used=true;
		}
		if (used==false){
			ChatBuilder.chatClient(ChatBuilder.createcolor(new String(I18n.format("signstory.command.text.set.fail.char")+" [&,%,$,@,{, and }]"), EnumChatFormatting.RED));
			ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.command.text.set.fail.char.after"), EnumChatFormatting.AQUA));
		}
	}
	private static void setStuff() {
		GuiMain.setContentId(Global_Vars.Text.get(0));
		CurrentMode.instance.setMode(CurrentMode.Mode.PLACE);
		CurrentMode.instance.setState(CurrentMode.State.PREVIEW, true);
	}
}
