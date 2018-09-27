package stevekung.mods.stevelightsaber;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLaserBullet extends Render<EntityArrow>
{
    private static final ResourceLocation texture = new ResourceLocation(LightSaberMod.MOD_ID + ":textures/model/laser_bullet.png");
    private static final ModelLaserBullet model = new ModelLaserBullet();

    public RenderLaserBullet(RenderManager manager)
    {
        super(manager);
    }

    @Override
    public void doRender(EntityArrow entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        float lightMapSaveX = OpenGlHelper.lastBrightnessX;
        float lightMapSaveY = OpenGlHelper.lastBrightnessY;
        float red = 255.0F, green = 0.0F, blue = 0.0F;
        this.bindEntityTexture(entity);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 90.0F, 0.0F, 1.0F);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);

        GlStateManager.color(red / 255.0F, green / 255.0F, blue / 255.0F, 1.0F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.disableLighting();
        this.bindEntityTexture(entity);
        GlStateManager.enableBlend();
        RenderLaserBullet.model.renderLaser();
        GlStateManager.disableBlend();

        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightMapSaveX, lightMapSaveY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityArrow entity)
    {
        return texture;
    }
}