package train.client.render.models.blocks;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelLargeSlopeTCTrack extends ModelBase {
	private static IModelCustom modeltrack = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_slope_long.obj"));
	private static IModelCustom modelLargeSlopeWood = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "supports_wood_long.obj"));
	private static IModelCustom modelLargeSlopeBallast = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "supports_ballast_long.obj"));
	public ModelLargeSlopeTCTrack() {
	}

	public void render(String type) {
		if(type.equals("wood")) {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_slope.png"));
			modelLargeSlopeWood.renderAll();
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
			modeltrack.renderAll();}
		if(type.equals("gravel")) {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, "textures/blocks/gravel.png"));
			modelLargeSlopeBallast.renderAll();
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
			modeltrack.renderAll();}
		if(type.equals("ballast")) {
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, "textures/blocks/ballast_test.png"));
			modelLargeSlopeBallast.renderAll();
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
			modeltrack.renderAll();}
	}

	public void render(String type, TileTCRail tcRail, double x, double y, double z) {

		GL11.glColor4f(1, 1, 1, 1);
		//GL11.glScalef(0.5f, 0.5f, 0.5f);
		int facing = tcRail.getWorldObj().getBlockMetadata(tcRail.xCoord, tcRail.yCoord, tcRail.zCoord);

		if (facing == 3) {
			GL11.glRotatef(-90, 0, 1, 0);
		}
		else if (facing == 1) {
			GL11.glRotatef(90, 0, 1, 0);
		}
		else if(facing == 0){
			GL11.glRotatef(180, 0, 1, 0);
		}
		//GL11.glTranslatef(0.0f, 0.0f, -1.0f);
		render(type);
	}
}