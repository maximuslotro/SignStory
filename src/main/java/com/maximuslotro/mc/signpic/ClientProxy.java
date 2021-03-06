package com.maximuslotro.mc.signpic;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;

import com.maximuslotro.mc.signpic.command.SignStoryCommand;
import com.maximuslotro.mc.signpic.events.EventListener;
import com.maximuslotro.mc.signpic.render.CustomItemSignRenderer;
import com.mojang.util.UUIDTypeAdapter;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.Session;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(final @Nonnull FMLPreInitializationEvent event) {
		super.preInit(event);

		// Setup stencil clip
		// StencilClip.init();

		// Setup location
		Client.initLocation(new Locations(event.getSourceFile(), getDataDirectory()));

		// Get Id
		final String id = Client.mc.getSession().getPlayerID();
		try {
			final Object o = UUIDTypeAdapter.fromString(id);
			if (o!=null) {
				Client.id = id;
				final Session s = Client.mc.getSession();
				Client.name = s.getUsername();
				Client.token = s.getToken();
			}
		} catch (final IllegalArgumentException e) {
		}
	}

	private @Nonnull File getDataDirectory() {
		final File file = Client.mc.mcDataDir;
		try {
			return file.getCanonicalFile();
		} catch (final IOException e) {
			Log.dev.error("Could not canonize path!", e);
		}
		return file;
	}

	@Override
	public void init(final @Nonnull FMLInitializationEvent event) {
		super.init(event);

		FMLCommonHandler.instance().bus().register(new EventListener());
		// Replace Sign Renderer
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySign.class, Client.renderer);
		MinecraftForgeClient.registerItemRenderer(Items.sign, new CustomItemSignRenderer());

		// Event Register
		Client.handler.init();
		try {
        	ClientCommandHandler.instance.registerCommand(new SignStoryCommand());
        } catch (Exception e) {
        	System.out.println("error registering one or more commands"+e);
        }
	}

	@Override
	public void postInit(final @Nonnull FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}