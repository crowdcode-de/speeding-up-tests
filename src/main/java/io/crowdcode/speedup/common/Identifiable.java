package io.crowdcode.speedup.common;

import java.io.Serializable;

public interface Identifiable<K extends Serializable> extends Serializable {

    K getId();

    <T extends Identifiable> T setId(K id);
}
