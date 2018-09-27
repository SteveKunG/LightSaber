package stevekung.mods.stevelightsaber;

import java.util.Arrays;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import stevekung.mods.stevekunglib.utils.CommonRegistryUtils;
import stevekung.mods.stevekunglib.utils.CommonUtils;

@Mod(modid = LightSaberMod.MOD_ID, name = LightSaberMod.NAME, version = LightSaberMod.VERSION, clientSideOnly = true)
public class LightSaberMod
{
    public static final String NAME = "Steve's Light Saber";
    public static final String MOD_ID = "steve's_light_saber";
    public static final int MAJOR_VERSION = 1;
    public static final int MINOR_VERSION = 0;
    public static final int BUILD_VERSION = 0;
    public static final String VERSION = LightSaberMod.MAJOR_VERSION + "." + LightSaberMod.MINOR_VERSION + "." + LightSaberMod.BUILD_VERSION;
    public static final CommonRegistryUtils COMMON_REGISTRY = new CommonRegistryUtils(LightSaberMod.MOD_ID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LightSaberMod.initModInfo(event.getModMetadata());
        CommonUtils.registerEventHandler(this);
        ModelLoader.setCustomModelResourceLocation(Items.DIAMOND_SWORD, 0, new ModelResourceLocation(LightSaberMod.MOD_ID + ":light_saber", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Items.GOLDEN_SWORD, 0, new ModelResourceLocation(LightSaberMod.MOD_ID + ":light_saber", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Items.IRON_SWORD, 0, new ModelResourceLocation(LightSaberMod.MOD_ID + ":light_saber", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Items.WOODEN_SWORD, 0, new ModelResourceLocation(LightSaberMod.MOD_ID + ":light_saber", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Items.STONE_SWORD, 0, new ModelResourceLocation(LightSaberMod.MOD_ID + ":light_saber", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Items.BOW, 0, new ModelResourceLocation(LightSaberMod.MOD_ID + ":laser_gun", "inventory"));
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.keySet().removeIf(key -> key.equals(EntityTippedArrow.class));
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityTippedArrow.class, new RenderLaserBullet(Minecraft.getMinecraft().getRenderManager()));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();

        if (mc.world != null && event.phase == Phase.START)
        {
            for (EntityPlayer player : mc.world.playerEntities)
            {
                if (player instanceof EntityOtherPlayerMP)
                {
                    EntityOtherPlayerMP otherPlayer = (EntityOtherPlayerMP) player;

                    if (!otherPlayer.getHeldItemMainhand().isEmpty() && otherPlayer.getHeldItemMainhand().getItem() instanceof ItemSword && otherPlayer.isSwingInProgress && otherPlayer.ticksExisted % 4 == 0)
                    {
                        mc.getSoundHandler().playSound(new PositionedSoundRecord(SoundEvent.REGISTRY.getObject(new ResourceLocation(LightSaberMod.MOD_ID + ":saber_swing")).getSoundName(), SoundCategory.PLAYERS, 0.4F, 1.0F, false, 0, ISound.AttenuationType.LINEAR, (float)otherPlayer.posX + 0.5F, (float)otherPlayer.posY + 0.5F, (float)otherPlayer.posZ + 0.5F));
                    }
                }
            }
            if (mc.player != null && mc.gameSettings.keyBindAttack.isKeyDown())
            {
                ItemStack itemStack = mc.player.getHeldItemMainhand();

                if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK)
                {
                    if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemSword && mc.player.ticksExisted % 4 == 0)
                    {
                        mc.player.playSound(SoundEvent.REGISTRY.getObject(new ResourceLocation(LightSaberMod.MOD_ID + ":saber_swing")), 0.8F, 1.0F);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onMouseClick(MouseEvent event)
    {
        int button = event.getButton() - 100;
        Minecraft mc = Minecraft.getMinecraft();
        ItemStack itemStack = mc.player.getHeldItemMainhand();
        int key = mc.gameSettings.keyBindAttack.getKeyCode();

        if (button == key && Mouse.isButtonDown(button + 100))
        {
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemSword)
            {
                mc.player.playSound(SoundEvent.REGISTRY.getObject(new ResourceLocation(LightSaberMod.MOD_ID + ":saber_swing")), 0.8F, 1.0F);
            }
        }
    }

    @SubscribeEvent
    public void onRegister(RegistryEvent.Register<SoundEvent> event)
    {
        ResourceLocation res = new ResourceLocation(LightSaberMod.MOD_ID + ":saber_swing");
        event.getRegistry().register(new SoundEvent(res).setRegistryName(res));
        res = new ResourceLocation(LightSaberMod.MOD_ID + ":laser_shoot");
        event.getRegistry().register(new SoundEvent(res).setRegistryName(res));
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event)
    {
        Items.DIAMOND_SWORD.setTileEntityItemStackRenderer(LightSaberRenderer.INSTANCE);
        Items.GOLDEN_SWORD.setTileEntityItemStackRenderer(LightSaberRenderer.INSTANCE);
        Items.IRON_SWORD.setTileEntityItemStackRenderer(LightSaberRenderer.INSTANCE);
        Items.WOODEN_SWORD.setTileEntityItemStackRenderer(LightSaberRenderer.INSTANCE);
        Items.STONE_SWORD.setTileEntityItemStackRenderer(LightSaberRenderer.INSTANCE);
        Items.BOW.setTileEntityItemStackRenderer(LightSaberRenderer.INSTANCE);
    }

    @SubscribeEvent
    public void onPlaySound(PlaySoundEvent event)
    {
        String name = event.getSound().getSoundLocation().toString();

        if (name.equals("minecraft:entity.arrow.shoot"))
        {
            event.setResultSound(new PositionedSoundRecord(SoundEvent.REGISTRY.getObject(new ResourceLocation(LightSaberMod.MOD_ID + ":laser_shoot")).getSoundName(), SoundCategory.PLAYERS, 0.2F, 1.0F, false, 0, ISound.AttenuationType.NONE, 0.0F, 0.0F, 0.0F));
        }
        else if (name.equals("minecraft:entity.arrow.hit"))
        {
            event.setResultSound(null);
        }
    }

    private static void initModInfo(ModMetadata info)
    {
        info.autogenerated = false;
        info.modId = LightSaberMod.MOD_ID;
        info.name = LightSaberMod.NAME;
        info.version = LightSaberMod.VERSION;
        info.description = "Light Saber for client side!";
        info.authorList = Arrays.asList("SteveKunG");
    }
}