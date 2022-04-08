package com.maximuslotro.mc.signpic.handler;

import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Sets;
import com.maximuslotro.mc.signpic.Client;
import com.maximuslotro.mc.signpic.Config;
import com.maximuslotro.mc.signpic.CoreEvent;
import com.maximuslotro.mc.signpic.Global_Vars;
import com.maximuslotro.mc.signpic.Log;
import com.maximuslotro.mc.signpic.attr.AttrReaders;
import com.maximuslotro.mc.signpic.attr.prop.OffsetData;
import com.maximuslotro.mc.signpic.attr.prop.SizeData;
import com.maximuslotro.mc.signpic.entry.Entry;
import com.maximuslotro.mc.signpic.entry.EntryId;
import com.maximuslotro.mc.signpic.entry.EntryId.ItemEntryId;
import com.maximuslotro.mc.signpic.entry.EntryId.SignEntryId;
import com.maximuslotro.mc.signpic.entry.content.ContentId;
import com.maximuslotro.mc.signpic.gui.GuiMain;
import com.maximuslotro.mc.signpic.gui.GuiSignOption;
import com.maximuslotro.mc.signpic.http.shortening.ShortenerApiUtil;
import com.maximuslotro.mc.signpic.mode.CurrentMode;
import com.maximuslotro.mc.signpic.preview.SignEntity;
import com.maximuslotro.mc.signpic.reflect.lib.ReflectClass;
import com.maximuslotro.mc.signpic.reflect.lib.ReflectField;
import com.maximuslotro.mc.signpic.util.ChatBuilder;
import com.maximuslotro.mc.signpic.util.ChatUtil;
import com.maximuslotro.mc.signpic.util.MathUtil;
import com.maximuslotro.mc.signpic.util.Sign;

import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class SignHandler {
	public static @Nonnull ReflectField<GuiEditSign, TileEntitySign> guiEditSignTileEntity = ReflectClass.fromClass(GuiEditSign.class).getFieldFromType(null, TileEntitySign.class);
	private static @Nonnull Set<INameHandler> handlers = Sets.newHashSet();

	public static void init() {
		handlers.add(new AnvilHandler());
	}

	private boolean isPlaceMode;

	@CoreEvent
	public void onSign(final @Nonnull GuiOpenEvent event) {
		for (final INameHandler handler : handlers)
			if (handler!=null)
				handler.reset();
		final EntryId handSign = CurrentMode.instance.getHandSign();
		final boolean handSignValid = handSign.entry().isValid();
		this.isPlaceMode = CurrentMode.instance.isMode(CurrentMode.Mode.PLACE);
		if (handSignValid||this.isPlaceMode) {
			final EntryId entryId = CurrentMode.instance.getEntryId();
			if (event.gui instanceof GuiEditSign) {
				event.setCanceled(true);
				final EntryId placeSign = handSignValid ? handSign : entryId;
				if (placeSign.isPlaceable())
					try {
						final TileEntitySign tileSign = guiEditSignTileEntity.get((GuiEditSign) event.gui);
						if (tileSign!=null) {
							Sign.placeSign(placeSign, tileSign);
							if (!CurrentMode.instance.isState(CurrentMode.State.CONTINUE)) {
								if(Config.getConfig().defaultUsage.get()==true) {
									CurrentMode.instance.setMode();
									}
								final SignEntity se = Sign.preview;
								if (se.isRenderable()) {
									final TileEntitySign preview = se.getTileEntity();
									if (preview.xCoord==tileSign.xCoord&&preview.yCoord==tileSign.yCoord&&preview.zCoord==tileSign.zCoord) {
										se.setVisible(false);
										CurrentMode.instance.setState(CurrentMode.State.PREVIEW, false);
										CurrentMode.instance.setState(CurrentMode.State.SEE, false);
									}
									if(Config.getConfig().defaultUsage.get()==false) {
										ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.chat.sign.placed")+(Global_Vars.CurrentPage+1)+I18n.format("signstory.chat.sign.placed.of")+Global_Vars.Text.size()+". "+MathUtil.round1(MathUtil.calculatePercentageToFloat((Global_Vars.CurrentPage+1), Global_Vars.Text.size()))+"%", EnumChatFormatting.GOLD));
										Global_Vars.CurrentPage++;
										if(Global_Vars.CurrentPage==(Global_Vars.Text.size()-1)) {
											ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.chat.sign.end.almost"), EnumChatFormatting.GREEN));
										}
										if(Global_Vars.CurrentPage>(Global_Vars.Text.size()-1)) {
											Global_Vars.CurrentPage=(Global_Vars.Text.size()-1);
											ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.chat.sign.end"), EnumChatFormatting.GREEN));
											ChatBuilder.chatClient(ChatBuilder.createcolor(I18n.format("signstory.chat.sign.page.back"), EnumChatFormatting.GOLD));
										}
										GuiMain.setContentId(Global_Vars.Text.get(Global_Vars.CurrentPage));
									}
								}
							}
						} else
							Log.notice(I18n.format("signpic.chat.error.place"));
					} catch (final Exception e) {
						Log.notice(I18n.format("signpic.chat.error.place"));
					}
				else if (CurrentMode.instance.isShortening())
					Log.notice(I18n.format("signpic.gui.notice.shortening"), 1f);
				else
					Log.notice(I18n.format("signpic.gui.notice.toolongplace"), 1f);
			} else {
				boolean b = false;
				for (final INameHandler handler : handlers)
					if (handler!=null)
						b = handler.onOpen(event.gui, entryId)||b;
				if (b) {
					if (!entryId.isNameable()) {
						final ContentId id = entryId.entry().contentId;
						if (id!=null)
							ShortenerApiUtil.requestShoretning(id);
					}
					if (!CurrentMode.instance.isState(CurrentMode.State.CONTINUE)) {
						CurrentMode.instance.setMode();
						Sign.preview.setVisible(false);
						CurrentMode.instance.setState(CurrentMode.State.PREVIEW, false);
						CurrentMode.instance.setState(CurrentMode.State.SEE, false);
					}
				}
			}
		}
	}

	@CoreEvent
	public void onTick() {
		if (this.isPlaceMode)
			for (final INameHandler handler : handlers)
				if (handler!=null)
					handler.onTick();
	}

	@CoreEvent
	public void onDraw(final @Nonnull GuiScreenEvent.DrawScreenEvent.Post event) {
		if (this.isPlaceMode)
			for (final INameHandler handler : handlers)
				if (handler!=null&&event.gui!=null)
					handler.onDraw(event.gui);
	}

	@CoreEvent
	public void onClick(final @Nonnull MouseEvent event) {
		if (event.buttonstate&&Client.mc.gameSettings.keyBindUseItem.getKeyCode()==event.button-100) {
			final ItemStack handItem = Client.mc.thePlayer.getCurrentEquippedItem();
			EntryId handEntry = null;
			if (handItem!=null&&handItem.getItem()==Items.sign) {
				handEntry = ItemEntryId.fromItemStack(handItem);
				CurrentMode.instance.setHandSign(handEntry);
			} else
				CurrentMode.instance.setHandSign(EntryId.blank);
			if (CurrentMode.instance.isMode(CurrentMode.Mode.SETPREVIEW)) {
				Sign.preview.capturePlace();
				event.setCanceled(true);
				CurrentMode.instance.setMode();
				Client.openEditor();
			} else if (CurrentMode.instance.isMode(CurrentMode.Mode.OPTION)) {
				final TileEntitySign tilesign = Client.getTileSignLooking();
				Entry entry = null;
				if (tilesign!=null)
					entry = SignEntryId.fromTile(tilesign).entry();
				else if (handEntry!=null)
					entry = handEntry.entry();
				if (entry!=null) {
					event.setCanceled(true);
					Client.mc.displayGuiScreen(new GuiSignOption(entry));
					if (!CurrentMode.instance.isState(CurrentMode.State.CONTINUE))
						CurrentMode.instance.setMode();
				}
			}
		}
	}

	@CoreEvent
	public void onTooltip(final @Nonnull ItemTooltipEvent event) {
		if (event.itemStack.getItem()==Items.sign) {
			final ItemEntryId id = ItemEntryId.fromItemStack(event.itemStack);
			final Entry entry = id.entry();
			if (entry.isValid()) {
				final String raw = !event.toolTip.isEmpty() ? event.toolTip.get(0) : "";
				if (id.hasName())
					event.toolTip.set(0, id.getName());
				else if (entry.contentId!=null)
					event.toolTip.set(0, I18n.format("signpic.item.sign.desc.named", entry.contentId.getURI()));
				final KeyBinding sneak = Client.mc.gameSettings.keyBindSneak;
				if (!Keyboard.isKeyDown(sneak.getKeyCode()))
					event.toolTip.add(I18n.format("signpic.item.hold", GameSettings.getKeyDisplayString(sneak.getKeyCode())));
				else {
					final AttrReaders meta = entry.getMeta();
					final SizeData size = meta.sizes.getMovie().get();
					event.toolTip.add(I18n.format("signpic.item.sign.desc.named.prop.size", size.getWidth(), size.getHeight()));
					final OffsetData offset = meta.offsets.getMovie().get();
					event.toolTip.add(I18n.format("signpic.item.sign.desc.named.prop.offset", offset.x.offset, offset.y.offset, offset.z.offset));
					// event.toolTip.add(I18n.format("signpic.item.sign.desc.named.prop.rotation", meta.rotation.compose()));
					if (id.hasName()&&entry.contentId!=null)
						event.toolTip.add(I18n.format("signpic.item.sign.desc.named.url", entry.contentId.getURI()));
					// event.toolTip.add(I18n.format("signpic.item.sign.desc.named.meta", meta.compose()));
					event.toolTip.add(I18n.format("signpic.item.sign.desc.named.raw", raw));
				}
			} else if (Config.getConfig().signTooltip.get()||!Config.getConfig().guiExperienced.get()) {
				final KeyBinding binding = KeyHandler.Keys.KEY_BINDING_GUI.binding;
				final List<KeyBinding> conflict = KeyHandler.getKeyConflict(binding);
				String keyDisplay = GameSettings.getKeyDisplayString(binding.getKeyCode());
				if (!conflict.isEmpty())
					keyDisplay = EnumChatFormatting.RED+keyDisplay;
				event.toolTip.add(I18n.format("signpic.item.sign.desc", keyDisplay));
				if (!conflict.isEmpty()) {
					event.toolTip.add(I18n.format("signpic.item.sign.desc.keyconflict", I18n.format("menu.options"), I18n.format("options.controls")));
					for (final KeyBinding key : conflict)
						event.toolTip.add(I18n.format("signpic.item.sign.desc.keyconflict.key", I18n.format(key.getKeyCategory()), I18n.format(key.getKeyDescription())));
				}
			}
		}
	}
}