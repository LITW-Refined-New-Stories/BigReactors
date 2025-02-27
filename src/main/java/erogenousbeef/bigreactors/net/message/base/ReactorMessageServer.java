package erogenousbeef.bigreactors.net.message.base;

import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import erogenousbeef.bigreactors.common.BRLog;
import erogenousbeef.bigreactors.common.multiblock.MultiblockReactor;
import erogenousbeef.bigreactors.common.multiblock.tileentity.TileEntityReactorPartBase;
import erogenousbeef.core.common.CoordTriplet;

public abstract class ReactorMessageServer extends WorldMessageServer {

    MultiblockReactor reactor;

    protected ReactorMessageServer() {
        super();
        reactor = null;
    }

    protected ReactorMessageServer(MultiblockReactor reactor, CoordTriplet referenceCoord) {
        super(referenceCoord.x, referenceCoord.y, referenceCoord.z);
        this.reactor = reactor;
    }

    protected ReactorMessageServer(MultiblockReactor reactor) {
        this(reactor, reactor.getReferenceCoord());
    }

    public static abstract class Handler<M extends ReactorMessageServer> extends WorldMessageServer.Handler<M> {

        protected abstract IMessage handleMessage(M message, MessageContext ctx, MultiblockReactor reactor);

        @Override
        protected IMessage handleMessage(M message, MessageContext ctx, TileEntity te) {
            if (te instanceof TileEntityReactorPartBase) {
                MultiblockReactor reactor = ((TileEntityReactorPartBase) te).getReactorController();
                if (reactor != null) {
                    return handleMessage(message, ctx, reactor);
                } else {
                    BRLog.error(
                        "Received ReactorMessageServer for a reactor part @ %d, %d, %d which has no attached reactor",
                        te.xCoord,
                        te.yCoord,
                        te.zCoord);
                }
            } else {
                BRLog.error(
                    "Received ReactorMessageServer for a non-reactor-part block @ %d, %d, %d",
                    message.x,
                    message.y,
                    message.z);
            }
            return null;
        }
    }
}
