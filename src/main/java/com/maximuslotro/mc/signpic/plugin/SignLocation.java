package com.maximuslotro.mc.signpic.plugin;

import javax.annotation.Nonnull;

import com.maximuslotro.mc.signpic.Client;

public class SignLocation {
	public @Nonnull String world;
	public int x;
	public int y;
	public int z;

	public SignLocation(final @Nonnull String world, final int x, final int y, final int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return Client.gson.toJson(this);
	}
}
