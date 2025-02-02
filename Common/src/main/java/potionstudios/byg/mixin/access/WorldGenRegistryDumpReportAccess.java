package potionstudios.byg.mixin.access;

import com.google.gson.JsonElement;
import com.mojang.serialization.DynamicOps;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.info.WorldgenRegistryDumpReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WorldgenRegistryDumpReport.class)
public interface WorldGenRegistryDumpReportAccess {

    @Invoker("dumpRegistryCap")
    static <T> void byg_invokeDumpRegistryCap(CachedOutput $$0, RegistryAccess $$1, DynamicOps<JsonElement> $$2, RegistryAccess.RegistryData<T> $$3) {
        throw new Error("Mixin did not apply");
    }
}
