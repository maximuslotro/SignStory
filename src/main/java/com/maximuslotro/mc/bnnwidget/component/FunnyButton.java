package com.maximuslotro.mc.bnnwidget.component;

import javax.annotation.Nonnull;

import com.maximuslotro.mc.bnnwidget.WEvent;
import com.maximuslotro.mc.bnnwidget.motion.Easings;
import com.maximuslotro.mc.bnnwidget.position.Area;
import com.maximuslotro.mc.bnnwidget.position.Point;
import com.maximuslotro.mc.bnnwidget.position.R;
import com.maximuslotro.mc.bnnwidget.render.OpenGL;
import com.maximuslotro.mc.bnnwidget.var.V;
import com.maximuslotro.mc.bnnwidget.var.VMotion;

/**
 * 少し斜めに傾かせることができるボタンです
 *
 * @author TeamFruit
 */
public class FunnyButton extends MButton {
	public FunnyButton(final @Nonnull R position) {
		super(position);
	}

	private boolean isHighlight = true;
	private boolean highlighted;
	/**
	 * 傾き (°)
	 */
	protected @Nonnull VMotion m = V.pm(0);
	/**
	 * 大きさの倍率 (%)
	 */
	protected @Nonnull VMotion s = V.pm(1);

	/**
	 * 傾いているかどうか
	 * @return 傾いている
	 */
	public boolean isHighlight() {
		return this.isHighlight;
	}

	/**
	 * 傾きを設定します
	 * @param b 傾いている場合true
	 */
	public void setHighlight(final boolean b) {
		this.isHighlight = b;
	}

	@Override
	public void update(final @Nonnull WEvent ev, final @Nonnull Area pgp, final @Nonnull Point p) {
		if (isHighlight()) {
			if (!this.highlighted) {
				this.highlighted = true;
				this.m.stop().add(Easings.easeOutElastic.move(.5f, 6f)).start();
				this.s.stop().add(Easings.easeOutElastic.move(.5f, 1.1f)).start();
			}
		} else if (this.highlighted) {
			this.highlighted = false;
			this.m.stop().add(Easings.easeOutElastic.move(.5f, 0f)).start();
			this.s.stop().add(Easings.easeOutElastic.move(.5f, 1f)).start();
		}
		super.update(ev, pgp, p);
	}

	@Override
	public void draw(final @Nonnull WEvent ev, final @Nonnull Area pgp, final @Nonnull Point p, final float frame, final float opacity) {
		final Area a = getGuiPosition(pgp);
		OpenGL.glPushMatrix();
		OpenGL.glTranslatef(a.x1()+a.w()/2, a.y1()+a.h()/2, 0);
		final float c = this.s.get();
		OpenGL.glScalef(c, c, 1f);
		OpenGL.glRotatef(this.m.get(), 0, 0, 1);
		OpenGL.glTranslatef(-a.x1()-a.w()/2, -a.y1()-a.h()/2, 0);
		super.draw(ev, pgp, p, frame, opacity);
		OpenGL.glPopMatrix();
	}
}