package mod.azure.doom.network.packets;

import mod.azure.doom.items.weapons.Ballista;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BallistaLoadingPacket {

	public int slot;

	public BallistaLoadingPacket(int slot) {
		this.slot = slot;
	}

	public BallistaLoadingPacket(final FriendlyByteBuf packetBuffer) {
		slot = packetBuffer.readInt();
	}

	public void encode(final FriendlyByteBuf packetBuffer) {
		packetBuffer.writeInt(slot);
	}

	public static void handle(BallistaLoadingPacket packet, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			final NetworkEvent.Context context = ctx.get();
			final PacketListener handler = context.getNetworkManager().getPacketListener();
			if (handler instanceof ServerGamePacketListenerImpl) {
				final ServerPlayer playerEntity = ((ServerGamePacketListenerImpl) handler).player;
				Ballista.reload(playerEntity, InteractionHand.MAIN_HAND);
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
