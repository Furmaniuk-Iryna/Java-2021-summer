package com.company.SecondTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Created by Iryna Furmaniuk on 20.07.2021.
 */
public class ArrayListWithoutRemoveAndModify<E> extends ArrayList {

    public ArrayListWithoutRemoveAndModify() {
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("You can`t remove the object!");
    }

    @Override
    public Object set(int index, Object element) {
        throw new UnsupportedOperationException("You can`t set new value!");
    }

    @Override
    public Object remove(int index) {
        throw new UnsupportedOperationException("You can`t remove the object!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("You can`t clear this ArrayList!");
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("You can`t remove in this ArrayList!");
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("You can`t remove all!");
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("You can`t use this method!");
    }

    @Override
    public boolean removeIf(Predicate filter) {
        throw new UnsupportedOperationException("You can`t remove in this ArrayList!");
    }

    @Override
    public void replaceAll(UnaryOperator operator) {
        throw new UnsupportedOperationException("You can`t replace in this ArrayList!");
    }
}

