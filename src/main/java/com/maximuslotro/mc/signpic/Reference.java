package com.maximuslotro.mc.signpic;

import javax.annotation.Nonnull;

public class Reference {
	public static final @Nonnull String MODID = "signstory";
	public static final @Nonnull String NAME = "SignStory";
	public static final @Nonnull String VERSION = "${version}";
	public static final @Nonnull String FORGE = "${forgeversion}";
	public static final @Nonnull String MINECRAFT = "${mcversion}";
	public static final @Nonnull String PROXY_SERVER = "com.maximuslotro.mc.signpic.CommonProxy";
	public static final @Nonnull String PROXY_CLIENT = "com.maximuslotro.mc.signpic.ClientProxy";
	public static final @Nonnull String GUI_FACTORY = "com.maximuslotro.mc.signpic.gui.config.ConfigGuiFactory";
}
