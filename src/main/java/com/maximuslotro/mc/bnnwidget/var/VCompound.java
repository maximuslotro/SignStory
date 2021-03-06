package com.maximuslotro.mc.bnnwidget.var;

import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.collect.Sets;


public class VCompound implements VCommon {

	protected @Nonnull VCommon coord;

	protected final @Nonnull Set<VCommon> coords;

	public VCompound(final @Nonnull VCommon a, final @Nonnull VCommon... b) {
		this.coord = a;
		this.coords = Sets.newHashSet(b);
	}

	@Override
	public float get() {
		float f = this.coord.get();
		for (final VCommon c : this.coords)
			f += c.get();
		return f;
	}

	@Override
	public float getAbsCoord(final float a, final float b) {
		float f = this.coord.getAbsCoord(a, b);
		for (final VCommon c : this.coords)
			f += c.getAbsCoord(0, b-a);
		return f;
	}

	@Override
	public String toString() {
		return String.format("VCompound [coord=%s, coords=%s]", this.coord, this.coords);
	}
}