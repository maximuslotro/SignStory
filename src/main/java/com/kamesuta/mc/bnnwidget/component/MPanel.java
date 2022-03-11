package com.kamesuta.mc.bnnwidget.component;

import static org.lwjgl.opengl.GL11.*;

import javax.annotation.Nonnull;

import com.kamesuta.mc.bnnwidget.WEvent;
import com.kamesuta.mc.bnnwidget.WPanel;
import com.kamesuta.mc.bnnwidget.position.Area;
import com.kamesuta.mc.bnnwidget.position.Point;
import com.kamesuta.mc.bnnwidget.position.R;
import com.kamesuta.mc.bnnwidget.render.OpenGL;
import com.kamesuta.mc.bnnwidget.render.WRenderer;

import net.minecraft.util.ResourceLocation;

/**
 * Minecraftãƒ‡ã‚¶ã‚¤ãƒ³ã�®ãƒ‘ãƒ�ãƒ«ã‚³ãƒ³ãƒ�ãƒ¼ãƒ�ãƒ³ãƒˆã�§ã�™ã€‚
 *
 * @author TeamFruit
 */
public class MPanel extends WPanel {
	/**
	 * BnnWidgetå�Œå°�ã�®Minecraftãƒ‡ã‚¶ã‚¤ãƒ³ã€�ãƒ‘ãƒ�ãƒ«ã�§ã�™ã€‚
	 */
	public static final @Nonnull ResourceLocation background = new ResourceLocation("bnnwidget", "textures/gui/background.png");
	/**
	 * BnnWidgetã�¯æ–°ãƒ‡ã‚¶ã‚¤ãƒ³ã‚’é–‹ç™ºä¸­ã�§ã�™ã€‚
	 */
	public static boolean tryNew;

	public MPanel(final @Nonnull R position) {
		super(position);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void draw(final @Nonnull WEvent ev, final @Nonnull Area pgp, final @Nonnull Point p, final float frame, final float popacity) {
		final Area a = getGuiPosition(pgp);
		final float op = getGuiOpacity(popacity);

		if (tryNew) {
			WRenderer.startShape();
			OpenGL.glColor4f(0f, 0f, 0f, op*.5f);
			draw(a);
			OpenGL.glLineWidth(1f);
			OpenGL.glColor4f(1f, 1f, 1f, op);
			draw(a, GL_LINE_LOOP);
		} else {
			WRenderer.startTexture();
			OpenGL.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			texture().bindTexture(background);
			drawBack(a);
		}
		super.draw(ev, pgp, p, frame, popacity);
	}

	/**
	 * èƒŒæ™¯ã‚’æ��ç”»ã�—ã�¾ã�™
	 * @param a çµ¶å¯¾åº§æ¨™
	 */
	public static void drawBack(final @Nonnull Area a) {
		drawTextureModal(Area.size(a.x1(), a.y1(), a.w()/2, a.h()/2), null, Area.size(0, 0, a.w()/2, a.h()/2));
		drawTextureModal(Area.size(a.x1()+a.w()/2, a.y1(), a.w()/2, a.h()/2), null, Area.size(256-a.w()/2, 0, a.w()/2, a.h()/2));
		drawTextureModal(Area.size(a.x1(), a.y1()+a.h()/2, a.w()/2, a.h()/2), null, Area.size(0, 256-a.h()/2, a.w()/2, a.h()/2));
		drawTextureModal(Area.size(a.x1()+a.w()/2, a.y1()+a.h()/2, a.w()/2, a.h()/2), null, Area.size(256-a.w()/2, 256-a.h()/2, a.w()/2, a.h()/2));
	}
}
