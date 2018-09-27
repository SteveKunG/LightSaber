package stevekung.mods.stevelightsaber;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelLightSaber extends ModelBase
{
    private ModelRenderer shape1;
    private ModelRenderer shape2;
    private ModelRenderer shape3;

    public ModelLightSaber()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape2 = new ModelRenderer(this, 0, 0);
        this.shape2.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.shape2.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3, 0.0F);
        this.shape3 = new ModelRenderer(this, 28, 0);
        this.shape3.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.shape3.addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F);
        this.shape1 = new ModelRenderer(this, 12, 0);
        this.shape1.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.shape1.addBox(-2.0F, -22.0F, -2.0F, 4, 22, 4, 0.0F);
    }

    public void renderSaber()
    {
        this.shape2.render(0.0625F);
        this.shape3.render(0.0625F);
    }

    public void renderBlade()
    {
        this.shape1.render(0.0625F);
    }
}