package logictechcorp.netherex.registry;

import com.geckolib.constant.dataticket.DataTicket;
import com.google.common.reflect.TypeToken;
import net.minecraft.resources.Identifier;

public class NetherExDataTickets
{
    public static final DataTicket<Identifier> VARIANT_TEXTURE = DataTicket.create("variant_texture", new TypeToken<>()
    {
    });
    public static final DataTicket<Boolean> IS_BABY = DataTicket.create("is_child", new TypeToken<>()
    {
    });
}
