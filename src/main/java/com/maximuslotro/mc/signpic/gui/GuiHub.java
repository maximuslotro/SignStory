package com.maximuslotro.mc.signpic.gui;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.maximuslotro.mc.bnnwidget.WEvent;
import com.maximuslotro.mc.bnnwidget.WFrame;
import com.maximuslotro.mc.bnnwidget.WPanel;
import com.maximuslotro.mc.bnnwidget.component.FontScaledLabel;
import com.maximuslotro.mc.bnnwidget.font.WFont;
import com.maximuslotro.mc.bnnwidget.motion.Easings;
import com.maximuslotro.mc.bnnwidget.motion.Motion;
import com.maximuslotro.mc.bnnwidget.position.Area;
import com.maximuslotro.mc.bnnwidget.position.Coord;
import com.maximuslotro.mc.bnnwidget.position.Point;
import com.maximuslotro.mc.bnnwidget.position.R;
import com.maximuslotro.mc.bnnwidget.render.WRenderer;
import com.maximuslotro.mc.bnnwidget.var.V;
import com.maximuslotro.mc.bnnwidget.var.VMotion;
import com.maximuslotro.mc.signpic.handler.KeyHandler;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiHub extends WFrame {
	public GuiHub(final @Nullable GuiScreen parent) {
		super(parent);
	}

	public GuiHub() {
	}

	{
		setGuiPauseGame(false);
	}

	@Override
	protected void initWidget() {
		add(new WPanel(new R(Coord.right(10), Coord.top(5), Coord.left(10), Coord.bottom(5))) {
			protected @Nonnull VMotion o = V.pm(0f).add(Motion.blank(1.5f).setAfter(new Runnable() {
				@Override
				public void run() {
					add(new FontScaledLabel(new R(Coord.top(0), Coord.height(9)), WFont.fontRenderer).setText(I18n.format("signpic.over.hub.usage.takerange", KeyHandler.keyScreenShot.getName())).setAlign(Align.LEFT).setShadow(true));
					add(new FontScaledLabel(new R(Coord.top(9), Coord.height(9)), WFont.fontRenderer).setText(I18n.format("signpic.over.hub.usage.takeall", KeyHandler.keyScreenShotFull.getName())).setAlign(Align.LEFT).setShadow(true));
					add(new FontScaledLabel(new R(Coord.top(18), Coord.height(9)), WFont.fontRenderer).setText(I18n.format("signpic.over.hub.usage.takerangewindow", KeyHandler.keySwingScreenShot.getName())).setAlign(Align.LEFT).setShadow(true));
					add(new FontScaledLabel(new R(Coord.top(27), Coord.height(9)), WFont.fontRenderer).setText(I18n.format("signpic.over.hub.usage.release")).setAlign(Align.LEFT).setShadow(true));
				}
			})).add(Easings.easeLinear.move(1f, 1f)).start();

			{
				this.opacity = this.o;
			}

			@Override
			protected void initWidget() {

			}
		});
	}

	@Override
	public void update(final WEvent ev, final Area pgp, final Point p) {
		KeyHandler.instance.keyHook(this);
		if (KeyHandler.keySignPicture.isKeyPressed())
			KeyHandler.instance.keyHook(this);
		else
			WRenderer.mc.displayGuiScreen(new GuiMain(this));
		if (!ev.isCurrent())
			requestClose();
		super.update(ev, pgp, p);
	}
}
