package com.kamesuta.mc.bnnwidget.render;

import static org.lwjgl.opengl.GL11.*;

import java.nio.IntBuffer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.bnnwidget.position.Area;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.util.EnumChatFormatting;

public class WGui extends WRenderer {
	public static final float textureScale = 1f/256f;

	public static final @Nonnull Area defaultTextureArea = Area.abs(0f, 0f, 1f, 1f);

	private static final @Nullable org.lwjgl.input.Cursor cur;

	static {
		org.lwjgl.input.Cursor cursor = null;
		try {
			final IntBuffer buf = GLAllocation.createDirectIntBuffer(1);
			buf.put(0);
			buf.flip();
			cursor = new org.lwjgl.input.Cursor(1, 1, 0, 0, 1, buf, null);
		} catch (final LWJGLException e) {
		}
		cur = cursor;
	}

	public static void setCursorVisible(final boolean b) {
		if (cur!=null)
			try {
				Mouse.setNativeCursor(b ? null : cur);
			} catch (final LWJGLException e) {
			}
	}

	public static void showCursor() {
		setCursorVisible(true);
	}

	public static void hideCursor() {
		setCursorVisible(false);
	}

	private static void drawTextureAbs(final float vx1, final float vy1, final float vx2, final float vy2, final float tx1, final float ty1, final float tx2, final float ty2) {
		beginTextureQuads()
				.pos(vx1, vy2, 0).tex(tx1, ty2)
				.pos(vx2, vy2, 0).tex(tx2, ty2)
				.pos(vx2, vy1, 0).tex(tx2, ty1)
				.pos(vx1, vy1, 0).tex(tx1, ty1)
				.draw();
	}

	private static void drawTextureAbsTrim(final float vx1, final float vy1, final float vx2, final float vy2, final float rx1, final float ry1, final float rx2, final float ry2, final float tx1, final float ty1, final float tx2, final float ty2) {
		final float ox1 = tx2-tx1;
		final float oy1 = ty2-ty1;
		final float ox2 = vx2-vx1;
		final float oy2 = vy2-vy1;
		final float ox3 = ox2/ox1;
		final float oy3 = oy2/oy1;
		final float ox4 = (rx1-vx1)/ox3;
		final float oy4 = (ry1-vy1)/oy3;
		final float ox5 = (rx2-vx1)/ox3;
		final float oy5 = (ry2-vy1)/oy3;
		final float ox6 = (rx2-rx1)/ox3;
		final float oy6 = (ry2-ry1)/oy3;
		final float ox7 = ox2/ox1*ox6;
		final float oy7 = oy2/oy1*oy6;
		beginTextureQuads()
				.pos(rx1, ry1+oy7, 0).tex(ox4+tx1, oy5+ty1)
				.pos(rx1+ox7, ry1+oy7, 0).tex(ox5+tx1, oy5+ty1)
				.pos(rx1+ox7, ry1, 0).tex(ox5+tx1, oy4+ty1)
				.pos(rx1, ry1, 0).tex(ox4+tx1, oy4+ty1)
				.draw();
	}


	@Deprecated
	private static void drawTextureAbsTrimOne(final float vx1, final float vy1, final float vx2, final float vy2, final float rx1, final float ry1, final float rx2, final float ry2, final float tx1, final float ty1, final float tx2, final float ty2) {
		drawTextureAbsTrim(vx1, vy1, vx2, vy2, Math.max(vx1, rx1), Math.max(vy1, ry1), Math.min(vx2, rx2), Math.min(vy2, ry2), tx1, ty1, tx2, ty2);
	}


	public static void drawTexture(@Nullable Area vertex, @Nullable Area trim, @Nullable Area texture) {
		if (vertex==null)
			vertex = defaultTextureArea;
		if (texture==null)
			texture = defaultTextureArea;
		if (trim!=null) {
			trim = vertex.trimArea(trim);
			if (trim!=null)
				drawTextureAbsTrim(vertex.x1(), vertex.y1(), vertex.x2(), vertex.y2(), trim.x1(), trim.y1(), trim.x2(), trim.y2(), texture.x1(), texture.y1(), texture.x2(), texture.y2());
		} else
			drawTextureAbs(vertex.x1(), vertex.y1(), vertex.x2(), vertex.y2(), texture.x1(), texture.y1(), texture.x2(), texture.y2());
	}


	public static void drawTextureModal(@Nullable final Area vertex, final @Nullable Area trim, @Nullable final Area texture) {
		drawTexture(vertex, trim, (texture!=null ? texture : defaultTextureArea).scale(textureScale));
	}


	private static void drawAbs(final float x1, final float y1, final float x2, final float y2, final int mode) {
		begin(mode)
				.pos(x1, y2, 0)
				.pos(x2, y2, 0)
				.pos(x2, y1, 0)
				.pos(x1, y1, 0)
				.draw();
	}


	public static void draw(@Nullable Area vertex, final int mode) {
		if (vertex==null)
			vertex = defaultTextureArea;
		drawAbs(vertex.x1(), vertex.y1(), vertex.x2(), vertex.y2(), mode);
	}


	public static void draw(@Nullable final Area vertex) {
		draw(vertex, GL_QUADS);
	}


	public static void drawString(final @Nonnull String text, final float x, final float y, final float w, final float h, final @Nonnull Align align, final @Nonnull VerticalAlign valign, final boolean shadow) {
		OpenGL.glPushMatrix();
		align.translate(text, x, w);
		valign.translate(text, y, h);
		buf.clear();
		GL11.glGetFloat(GL11.GL_CURRENT_COLOR, buf);
		final float r = buf.get(0);
		final float g = buf.get(1);
		final float b = buf.get(2);
		final float a = buf.get(3);
		OpenGL.glColor4f(1f, 1f, 1f, 1f);
		font().drawString(text, 0, 0, Math.max((int) (a*255+0.5)&0xff, 0x4)<<24|((int) (r*255+0.5)&0xFF)<<16|((int) (g*255+0.5)&0xFF)<<8|((int) (b*255+0.5)&0xFF)<<0, shadow);
		OpenGL.glColor4f(r, g, b, a);
		OpenGL.glPopMatrix();
	}


	public static void drawString(final @Nonnull String text, final @Nonnull Area a, final @Nonnull Align align, final @Nonnull VerticalAlign valign, final boolean shadow) {
		drawString(text, a.x1(), a.y1(), a.w(), a.h(), align, valign, shadow);
	}


	public static enum Align {
		LEFT {
			@Override
			protected void translate(final @Nonnull String text, final float x, final float w) {
				OpenGL.glTranslatef(x, 0, 0);
			}
		},
		CENTER {
			@Override
			protected void translate(final @Nonnull String text, final float x, final float w) {
				OpenGL.glTranslatef(x+(w-getStringWidth(text))/2, 0, 0);
			}
		},
		RIGHT {
			@Override
			protected void translate(final @Nonnull String text, final float x, final float w) {
				OpenGL.glTranslatef(x-getStringWidth(text), 0, 0);
			}
		},
		;
		protected abstract void translate(@Nonnull String text, float x, float w);
	}
	
	public static enum VerticalAlign {
		TOP {
			@Override
			protected void translate(final @Nonnull String text, final float y, final float h) {
				OpenGL.glTranslatef(0, y, 0);
			}
		},
		MIDDLE {
			@Override
			protected void translate(final @Nonnull String text, final float y, final float h) {
				OpenGL.glTranslatef(0, y+(h-font().FONT_HEIGHT)/2, 0);
			}
		},
		BOTTOM {
			@Override
			protected void translate(final @Nonnull String text, final float y, final float h) {
				OpenGL.glTranslatef(0, y+h-font().FONT_HEIGHT, 0);
			}
		},
		;
		protected abstract void translate(@Nonnull String text, float y, float h);
	}

	public static int getStringWidth(final @Nonnull String s) {
		if (StringUtils.isEmpty(s))
			return 0;
		return font().getStringWidth(EnumChatFormatting.getTextWithoutFormattingCodes(s));
	}
}
