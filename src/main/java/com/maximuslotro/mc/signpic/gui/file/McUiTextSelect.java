package com.maximuslotro.mc.signpic.gui.file;

import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;

import com.maximuslotro.mc.signpic.Client;
import com.maximuslotro.mc.signpic.Config;
import com.maximuslotro.mc.signpic.http.upload.UploadCallback;
import com.maximuslotro.mc.signpic.mode.CurrentMode;
import com.maximuslotro.mc.signpic.util.FileUtilitiy;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class McUiTextSelect extends UiTextSelect {
	public static final @Nonnull McUiTextSelect instance = new McUiTextSelect();

	@Override
	protected void initialize() {
		super.initialize();
		if (this.frame!=null)
			this.frame.setAlwaysOnTop(true);
	}

	@SuppressWarnings("resource")
	@Override
	protected @Nullable BufferedImage getImage(final @Nonnull String path) {
		try {
			return ImageIO.read(Client.mc.getResourceManager().getResource(new ResourceLocation("signpic", path)).getInputStream());
		} catch (final IOException e) {
		}
		return null;
	}

	@Override
	protected @Nonnull String getString(final @Nonnull String id) {
		return I18n.format(id);
	}

	@Override
	protected void transfer(final @Nonnull Transferable transferable) {
		if (FileUtilitiy.transfer(transferable, UploadCallback.copyOnDone))
			if (!CurrentMode.instance.isState(CurrentMode.State.CONTINUE))
				close();
	}

	@Override
	protected void apply(final @Nonnull File f) {
		FileUtilitiy.loadTextFile(f);
		close();
	}

	public void setVisible(final boolean b) {
		if (b)
			requestOpen();
		else
			requestClose();
	}
}
