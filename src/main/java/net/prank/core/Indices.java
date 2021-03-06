package net.prank.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Keep track of the original index of a solution in a collection and
 * an adjusted index after some preferred sort algorithm. This is useful
 * for identifying differences between 3rd party ordering and localized
 * ordering.
 *
 * @author dmillett
 *
 * Copyright 2012 David Millett
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */
public class Indices
    implements Serializable {

    private static final long serialVersionUID = 42L;
    /** A collection of indices for any object that is subject to multiple sorts */
    private final List<Integer> _indices;

    public Indices(int originalIndex) {
        _indices = new ArrayList<>();
        _indices.add(originalIndex);
    }

    public int getOriginalIndex() {
        return _indices.get(0);
    }

    public int getLastIndex() {
        return _indices.get(_indices.size() - 1);
    }

    public List<Integer> getIndices() {
        return new ArrayList<>(_indices);
    }

    public void updateWithCurrentIndex(int currentIndex) {
        _indices.add(currentIndex);
    }

    @Override
    public boolean equals(Object o) {

        if ( this == o )
        {
            return true;
        }

        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        Indices indices = (Indices) o;
        return _indices.equals(indices._indices);
    }

    @Override
    public int hashCode() {
        return _indices.hashCode();
    }

    @Override
    public String toString() {
        return "Indices{" +
                "_indices=" + _indices +
                '}';
    }
}
