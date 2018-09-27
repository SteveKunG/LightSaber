package stevekung.mods.stevelightsaber;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.stevekunglib.utils.ColorUtils;

@SideOnly(Side.CLIENT)
public class LightSaberRenderer extends TileEntityItemStackRenderer
{
    public static final LightSaberRenderer INSTANCE = new LightSaberRenderer();
    private static final ModelLightSaber model = new ModelLightSaber();
    private static final ModelLaserGun modelLaserGun = new ModelLaserGun();
    private static final ResourceLocation texture = new ResourceLocation(LightSaberMod.MOD_ID + ":textures/model/light_saber.png");
    private static final ResourceLocation textureGlow = new ResourceLocation(LightSaberMod.MOD_ID + ":textures/model/light_saber_glow.png");
    private static final ResourceLocation textureLaserGun = new ResourceLocation(LightSaberMod.MOD_ID + ":textures/model/laser_gun.png");
    private static final ResourceLocation textureLaserGunGlow = new ResourceLocation(LightSaberMod.MOD_ID + ":textures/model/laser_gun_glow.png");
    private static final ResourceLocation textureLaserGunGlow1 = new ResourceLocation(LightSaberMod.MOD_ID + ":textures/model/laser_gun_glow1.png");
    private static final ResourceLocation textureLaserGunGlow2 = new ResourceLocation(LightSaberMod.MOD_ID + ":textures/model/laser_gun_glow2.png");
    private static final ResourceLocation textureLaserGunGlow3 = new ResourceLocation(LightSaberMod.MOD_ID + ":textures/model/laser_gun_glow3.png");

    @Override
    public void renderByItem(ItemStack itemStack)
    {
        if (itemStack.getItem() == Items.DIAMOND_SWORD)
        {
            LightSaberRenderer.renderItem(ColorUtils.rgbToDecimal(51, 235, 203));
        }
        else if (itemStack.getItem() == Items.IRON_SWORD)
        {
            LightSaberRenderer.renderItem(ColorUtils.rgbToDecimal(216, 216, 216));
        }
        else if (itemStack.getItem() == Items.GOLDEN_SWORD)
        {
            LightSaberRenderer.renderItem(ColorUtils.rgbToDecimal(234, 238, 87));
        }
        else if (itemStack.getItem() == Items.STONE_SWORD)
        {
            LightSaberRenderer.renderItem(ColorUtils.rgbToDecimal(137, 137, 137));
        }
        else if (itemStack.getItem() == Items.WOODEN_SWORD)
        {
            LightSaberRenderer.renderItem(ColorUtils.rgbToDecimal(134, 101, 38));
        }
        else if (itemStack.getItem() == Items.BOW)
        {
            if (Minecraft.getMinecraft().player.isHandActive())
            {
                int i = itemStack.getMaxItemUseDuration() - Minecraft.getMinecraft().player.getItemInUseCount();

                if (i >= 18)
                {
                    LightSaberRenderer.renderLaserGun(3);
                }
                else if (i > 13)
                {
                    LightSaberRenderer.renderLaserGun(2);
                }
                else if (i > 0)
                {
                    LightSaberRenderer.renderLaserGun(1);
                }
            }
            else
            {
                LightSaberRenderer.renderLaserGun(0);
            }
        }
    }

    private static void renderItem(int color)
    {
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 1.5F, 0.0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        LightSaberRenderer.model.renderSaber();
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        GlStateManager.color(red, green, blue, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(textureGlow);
        GlStateManager.translate(0.0F, -0.45F, 0.0F);
        GlStateManager.scale(1.0F, 1.5F, 1.0F);
        GlStateManager.enableBlend();
        LightSaberRenderer.model.renderBlade();
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.popMatrix();
        GlStateManager.enableBlend();
    }

    private static void renderLaserGun(int state)
    {
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(textureLaserGun);
        LightSaberRenderer.modelLaserGun.renderBase();
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(state == 1 ? textureLaserGunGlow1 : state == 2 ? textureLaserGunGlow2 : state == 3 ? textureLaserGunGlow3 : textureLaserGunGlow);
        GlStateManager.enableBlend();
        LightSaberRenderer.modelLaserGun.renderGlowPart();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.popMatrix();
        GlStateManager.enableBlend();
    }
}