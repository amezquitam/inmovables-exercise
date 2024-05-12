package com.mehill.unmovables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mehill.unmovables.entity.AbstractUnmovable;

public class UnmovableManager {
    private ArrayList<AbstractUnmovable> unmovables;

    public UnmovableManager() {
        this.unmovables = new ArrayList<>();
    }

    public void add(AbstractUnmovable unmovable) {
        Boolean repeatedCode = unmovables.stream()
                .anyMatch(u -> u.getCode() == unmovable.getCode());

        if (repeatedCode) {
            throw new RuntimeException(String.format("A Unmovable with code %d already exist", unmovable.getCode()));
        }

        unmovables.add(unmovable);
    }

    public void addAll(Collection<AbstractUnmovable> unmovableCollection) {
        unmovableCollection.forEach(this::add);
    }

    public AbstractUnmovable getByCode(Integer code) {
        return unmovables.stream()
                .filter(unmovable -> unmovable.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public List<AbstractUnmovable> filter(Predicate<AbstractUnmovable> pricate) {
        return unmovables.stream()
                .filter(pricate)
                .collect(Collectors.toList());
    }

    public Stream<AbstractUnmovable> stream() {
        return unmovables.stream();
    }

    public int size() {
        return unmovables.size();
    }
}
