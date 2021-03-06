package org.mcupdater.autopackager.compat;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.mcupdater.autopackager.AutoPackager;
import org.mcupdater.autopackager.BlockPackager;
import org.mcupdater.autopackager.TilePackager;

import java.util.List;

/**
 * Created by sbarbour on 2/21/16.
 */
public class AutoPackagerDataProvider implements IWailaDataProvider {
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
		if (iWailaDataAccessor.getBlock() instanceof BlockPackager) {
			return new ItemStack(AutoPackager.packagerBlock, 1, 0);
		}
		else {
			return null;
		}
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
		return list;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
		TileEntity tile = iWailaDataAccessor.getTileEntity();
		if (tile != null && tile instanceof TilePackager) {
			((TilePackager) tile).addWailaInformation(list);
		}
		return list;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
		return list;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP entityPlayerMP, TileEntity tileEntity, NBTTagCompound nbtTagCompound, World world, int i, int i1, int i2) {
		if (tileEntity != null) {
			tileEntity.writeToNBT(nbtTagCompound);
		}
		return nbtTagCompound;
	}
}
