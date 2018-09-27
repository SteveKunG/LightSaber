package stevekung.mods.stevelightsaber;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelLaserGun extends ModelBase
{
    private ModelRenderer shape1;
    private ModelRenderer shape2;
    private ModelRenderer shape3;
    private ModelRenderer shape4;
    private ModelRenderer shape5;
    private ModelRenderer shape6;
    private ModelRenderer shape7;
    private ModelRenderer shape8;
    private ModelRenderer shape9;
    private ModelRenderer shape10;
    private ModelRenderer shape11;
    private ModelRenderer shape12;
    private ModelRenderer shape13;

    public ModelLaserGun()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.shape6 = new ModelRenderer(this, 35, 8);
        this.shape6.setRotationPoint(0.0F, -1.5F, -11.5F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.shape11 = new ModelRenderer(this, 32, 31);
        this.shape11.setRotationPoint(0.0F, -1.8F, 0.8F);
        this.shape11.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.shape5 = new ModelRenderer(this, 28, 8);
        this.shape5.setRotationPoint(-0.5F, -2.0F, -10.5F);
        this.shape5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.shape10 = new ModelRenderer(this, 17, 28);
        this.shape10.setRotationPoint(-0.5F, -3.0F, -4.5F);
        this.shape10.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.shape7 = new ModelRenderer(this, 42, 8);
        this.shape7.setRotationPoint(0.0F, 1.0F, -3.0F);
        this.shape7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.shape12 = new ModelRenderer(this, 18, 35);
        this.shape12.setRotationPoint(-1.5F, -1.3F, -8.0F);
        this.shape12.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.shape2 = new ModelRenderer(this, 35, 0);
        this.shape2.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.shape1 = new ModelRenderer(this, 30, 0);
        this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.shape9 = new ModelRenderer(this, 39, 19);
        this.shape9.setRotationPoint(-0.5F, 0.0F, -9.5F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.shape8 = new ModelRenderer(this, 49, 9);
        this.shape8.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.shape8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.shape3 = new ModelRenderer(this, 10, 12);
        this.shape3.setRotationPoint(0.0F, -2.0F, 1.0F);
        this.shape3.addBox(-1.0F, 0.0F, -11.0F, 3, 2, 11, 0.0F);
        this.shape4 = new ModelRenderer(this, 21, 8);
        this.shape4.setRotationPoint(0.0F, -3.0F, -8.0F);
        this.shape4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.shape13 = new ModelRenderer(this, 18, 44);
        this.shape13.setRotationPoint(1.5F, -1.3F, -8.0F);
        this.shape13.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
    }

    public void renderBase()
    {
        this.shape6.render(0.0625F);
        this.shape11.render(0.0625F);
        this.shape5.render(0.0625F);
        this.shape10.render(0.0625F);
        this.shape7.render(0.0625F);
        this.shape12.render(0.0625F);
        this.shape2.render(0.0625F);
        this.shape1.render(0.0625F);
        this.shape9.render(0.0625F);
        this.shape8.render(0.0625F);
        this.shape3.render(0.0625F);
        this.shape4.render(0.0625F);
        this.shape13.render(0.0625F);
    }

    public void renderGlowPart()
    {
        this.shape6.render(0.0625F);
        this.shape11.render(0.0625F);
        this.shape5.render(0.0625F);
        this.shape10.render(0.0625F);
        this.shape7.render(0.0625F);
        this.shape12.render(0.0625F);
        this.shape2.render(0.0625F);
        this.shape1.render(0.0625F);
        this.shape9.render(0.0625F);
        this.shape8.render(0.0625F);
        this.shape3.render(0.0625F);
        this.shape4.render(0.0625F);
        this.shape13.render(0.0625F);
    }
}