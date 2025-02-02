package potionstudios.byg.datagen.util;

import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import potionstudios.byg.reg.RegistrationProvider;
import potionstudios.byg.reg.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class PredicatedTagProvider<T> {
    private final RegistrationProvider<T> provider;
    private final List<Info<T>> predicates = new ArrayList<>();

    public PredicatedTagProvider(RegistrationProvider<T> provider) {
        this.provider = provider;
    }

    public PredicatedTagProvider<T> forInstance(Class<?> clazz, TagKey<T> tag) {
        this.predicates.add(new Info<>(clazz::isInstance, tag));
        return this;
    }

    public PredicatedTagProvider<T> add(Predicate<? super T> predicate, TagKey<T> tag) {
        this.predicates.add(new Info<>(predicate, tag));
        return this;
    }

    public void run(Function<TagKey<T>, TagsProvider.TagAppender<T>> function) {
        this.provider.getEntries().stream()
                .map(RegistryObject::get)
                .forEach(obj -> predicates.forEach(info -> {
                    if (info.predicate.test(obj))
                        function.apply(info.tag()).add(obj);
                }));
    }

    private record Info<T>(Predicate<? super T> predicate, TagKey<T> tag) {}
}
