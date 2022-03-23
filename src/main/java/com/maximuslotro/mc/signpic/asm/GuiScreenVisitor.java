package com.maximuslotro.mc.signpic.asm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.maximuslotro.mc.signpic.asm.lib.DescHelper;
import com.maximuslotro.mc.signpic.asm.lib.MethodMatcher;

public class GuiScreenVisitor extends ClassVisitor {
	private static class HookMethodVisitor extends MethodVisitor {
		public HookMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			/*
			 0  getstatic com.maximuslotro.mc.signpic.handler.KeyHandler.instance : com.maximuslotro.mc.signpic.handler.KeyHandler [44]
			 3  aload_0 [this]
			 4  invokevirtual com.maximuslotro.mc.signpic.handler.KeyHandler.onGuiKeyInput(net.minecraft.client.gui.GuiScreen) : boolean [50]
			 7  ifeq 11
			10  return
			*/
			super.visitFieldInsn(Opcodes.GETSTATIC, "com.maximuslotro/mc/signpic/handler/KeyHandler", "instance", DescHelper.toDesc("com.maximuslotro.mc.signpic.handler.KeyHandler"));
			super.visitVarInsn(Opcodes.ALOAD, 0);
			super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com.maximuslotro/mc/signpic/handler/KeyHandler", "onGuiKeyInput", DescHelper.toDesc(boolean.class, "net.minecraft.client.gui.GuiScreen"), false);
			final Label label = new Label();
			super.visitJumpInsn(Opcodes.IFEQ, label);
			super.visitInsn(Opcodes.RETURN);
			super.visitLabel(label);
			super.visitCode();
		}
	}

	private final MethodMatcher matcher;

	public GuiScreenVisitor(final @Nonnull String obfClassName, final @Nonnull ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		this.matcher = new MethodMatcher(obfClassName, DescHelper.toDesc(void.class, new Object[0]), ASMDeobfNames.GuiScreenHandleInput);
	}

	@Override
	public @Nullable MethodVisitor visitMethod(final int access, final @Nullable String name, final @Nullable String desc, final @Nullable String signature, final @Nullable String[] exceptions) {
		final MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		if (name==null||desc==null)
			return parent;
		return this.matcher.match(name, desc) ? new HookMethodVisitor(parent) : parent;
	}
}