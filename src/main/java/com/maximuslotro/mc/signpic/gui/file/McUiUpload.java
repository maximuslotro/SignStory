package com.maximuslotro.mc.signpic.gui.file;

import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;

import com.maximuslotro.mc.signpic.Client;
import com.maximuslotro.mc.signpic.http.upload.UploadApiUtil;
import com.maximuslotro.mc.signpic.http.upload.UploadCallback;
import com.maximuslotro.mc.signpic.http.upload.UploadRequest;
import com.maximuslotro.mc.signpic.mode.CurrentMode;
import com.maximuslotro.mc.signpic.state.State;
import com.maximuslotro.mc.signpic.util.FileUtilitiy;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class McUiUpload extends UiUpload {
	public static final @Nonnull McUiUpload instance = new McUiUpload();

	@Override
	protected void initialize() {
		super.initialize();
		if (this.frame!=null)
			this.frame.setAlwaysOnTop(true);
	}

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
		if (UploadApiUtil.upload(UploadRequest.fromFile(f, new State()), UploadCallback.copyOnDone))
			if (!CurrentMode.instance.isState(CurrentMode.State.CONTINUE))
				close();
	}

	public void setVisible(final boolean b) {
		if (b)
			requestOpen();
		else
			requestClose();
	}
}