package com.maximuslotro.mc.bnnwidget;

import javax.annotation.Nonnull;

import com.maximuslotro.mc.bnnwidget.position.R;

/**
 * デフォルトのパネル実装です。パネルの中にウィジェットを配置することができます。
 *
 * @author TeamFruit
 */
public class WPanel extends WTypedPanel<WCommon> {
	public WPanel(final @Nonnull R position) {
		super(position);
	}
}
